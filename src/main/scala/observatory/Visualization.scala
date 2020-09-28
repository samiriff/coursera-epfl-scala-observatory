package observatory

import com.sksamuel.scrimage.{Image, Pixel}

/**
  * 2nd milestone: basic visualization
  */
object Visualization extends VisualizationInterface {

  private val radius = 6378.137
  private val p = 2
  private val pi = 3.141592654
  private val width = 360
  private val height = 180

  /**
    * @param temperatures Known temperatures: pairs containing a location and the temperature at this location
    * @param location Location where to predict the temperature
    * @return The predicted temperature at `location`
    */
  def predictTemperature(temperatures: Iterable[(Location, Temperature)], location: Location): Temperature = {
    val temperaturesList = temperatures.map(_._2)
    val locationRadians = Location(location.lat * pi / 180.0, location.lon * pi / 180.0)
    val existingLocationsRadians = temperatures.map(_._1)
      .map {
        case existingLocation => 
          Location(existingLocation.lat * pi / 180.0, existingLocation.lon * pi / 180.0)
      }
    val distances = existingLocationsRadians.map {
      case existingLocation => 
        if (existingLocation.lat == locationRadians.lat && existingLocation.lon == locationRadians.lon) 0
        else if(existingLocation.lat == -locationRadians.lat && (existingLocation.lon == 180 - locationRadians.lon)) pi
        else {
          scala.math.acos(
            scala.math.sin(existingLocation.lat) * scala.math.sin(locationRadians.lat) +
              scala.math.cos(existingLocation.lat) * scala.math.cos(locationRadians.lat) * scala.math.cos(scala.math.abs(existingLocation.lon - locationRadians.lon))
          )
        }
      }
      .map(_ * radius)

    if (distances.filter(_ <= 1.0).nonEmpty) {
      distances.zip(temperaturesList).toMap.min._2
    } else {
      val inverseDistances = distances.map((1 / scala.math.pow(_, p)))
      val inverseDistancesSum = inverseDistances.sum

      val weightedInverseDistancesSum = inverseDistances.zip(temperaturesList).map {
        case (inverseDistance, temperature) => 
          inverseDistance * temperature
      }.sum

      weightedInverseDistancesSum / inverseDistancesSum
    }
  }

  /**
    * @param points Pairs containing a value and its associated color
    * @param value The value to interpolate
    * @return The color that corresponds to `value`, according to the color scale defined by `points`
    */
  def interpolateColor(points: Iterable[(Temperature, Color)], value: Temperature): Color = {
    val sortedPoints = points.toList.sortWith(_._1 < _._1)
    //println("Sorted points = ", sortedPoints)
    val lowIndex = sortedPoints.size - sortedPoints.map(_._1)
      .map(_ - value)
      .filter(_ > 0)
      .size

    if (lowIndex == 0) {
      sortedPoints(0)._2
    } else if (lowIndex == points.size) {
      sortedPoints(lowIndex - 1)._2
    } else {
      val firstPoint = sortedPoints(lowIndex - 1)
      val secondPoint = sortedPoints(lowIndex)
      //println("First Point = ", firstPoint)
      //println("Second Point = ", secondPoint)
      val (firstTemperature, firstColor) = firstPoint
      val (secondTemperature, secondColor) = secondPoint
      Color(
        getInterpolatedColorComponent(firstColor.red, secondColor.red, firstTemperature, secondTemperature, value),
        getInterpolatedColorComponent(firstColor.green, secondColor.green, firstTemperature, secondTemperature, value),
        getInterpolatedColorComponent(firstColor.blue, secondColor.blue, firstTemperature, secondTemperature, value)
      )
    }
  }

  private def getInterpolatedColorComponent(firstColorComponent: Int, secondColorComponent: Int, firstTemperature: Temperature, secondTemperature: Temperature, value: Temperature) = {
    (firstColorComponent + ((secondColorComponent - firstColorComponent).toDouble / (secondTemperature - firstTemperature) * (value - firstTemperature))).round.toInt
  }

  /**
    * @param temperatures Known temperatures
    * @param colors Color scale
    * @return A 360×180 image where each pixel shows the predicted temperature at its location
    */
  def visualize(temperatures: Iterable[(Location, Temperature)], colors: Iterable[(Temperature, Color)]): Image = {
    val pixels = Array.fill[Pixel](360 * 180)(new Pixel(getARGBPackedInt(Color(0, 0, 0))))
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val longitude = x - 180
        val latitude = 90 - y
        val requiredLocation = Location(latitude, longitude)
        val predictedTemperature = predictTemperature(temperatures, requiredLocation)
        val interpolatedColor = interpolateColor(colors, predictedTemperature)
        //println("Interpolated Color = ", interpolatedColor)
        val arrIndex = y * width + x
        pixels(arrIndex) = new Pixel(getARGBPackedInt(interpolatedColor))
      }
    }
    //println("Pixels = ", pixels.toList)
    Image(width, height, pixels)
  }

  private def getARGBPackedInt(color: Color) = {
    (255 << 24) + (color.red << 16) + (color.green << 8) + color.blue
  }

}


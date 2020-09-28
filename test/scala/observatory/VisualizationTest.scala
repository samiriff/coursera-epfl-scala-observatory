package observatory

import org.junit.Test

trait VisualizationTest extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("raw data display", 2) _

  private val points = Seq[(Temperature, Color)](
    (60, Color(255, 255, 255)),
    (32, Color(255, 0, 0)),
    (12, Color(255, 255, 0)),
    (0, Color(0, 255, 255)),
    (-15, Color(0, 0, 255)),
    (-27, Color(255, 0, 255)),
    (-50, Color(33, 0, 107)),
    (-60, Color(0, 0, 0))
  )

  // Implement tests for the methods of the `Visualization` object
  /*@Test def `test predictTemperature`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(-20, -20), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val prediction = Visualization.predictTemperature(temperatures, Location(0, 0))
    assert(scala.math.abs(prediction - 25) <= 0.001)
  }

  // Implement tests for the methods of the `Visualization` object
  @Test def `test predictTemperature for corner cases`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(0, 0), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val prediction = Visualization.predictTemperature(temperatures, Location(1, 1))
    assert(scala.math.abs(prediction - 20.05602052194179) <= 0.001)
  }

  // Implement tests for the methods of the `Visualization` object
  @Test def `test predictTemperature for NaN results`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(0, 0), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val prediction = Visualization.predictTemperature(temperatures, Location(0, 0))
    println("HAHAHA = " + prediction)
    assert(scala.math.abs(prediction - 20) <= 0.001)
  }

  // Implement tests for the methods of the `Visualization` object
  @Test def `predicted temperature at location z should be closer to known temperature at location x than to known temperature at location y, if z is closer (in distance) to x than y, and vice versa`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(29, 72), 10),
      (Location(35, 75), 20)
    )
    val prediction = Visualization.predictTemperature(temperatures, Location(32.0, 73.0))
    assert(scala.math.abs(prediction - 14.526) <= 0.001)
  }

  @Test def `test interpolateColor for exact match`(): Unit = milestoneTest {
    points.foreach {
      case (temperature, color) => 
        assert(Visualization.interpolateColor(points, temperature) == color)
    }
  }

  @Test def `test interpolateColor with dummy data`(): Unit = milestoneTest {
    assert(Visualization.interpolateColor(points, -25) == Color(213,0,255))    
  }

  @Test def `test visualize with dummy data`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(-20, -20), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val image = Visualization.visualize(temperatures, points)
    println("HAHA = ", image)
    image.output(new java.io.File("test.png"))
  }*/
}

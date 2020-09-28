package observatory

import org.junit.Test

trait Visualization2Test extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("value-added information visualization", 5) _

  // Implement tests for methods of the `Visualization2` object
  /*@Test def `test bilinearInterpolation`(): Unit = milestoneTest {
    val interpolatedTemperature = Visualization2.bilinearInterpolation(
      CellPoint(0.1, 0.0), 
      0.0,
      0.0,
      1.52587890625E-5,
      0.0
    )
    assert(interpolatedTemperature - 1.52587890625E-6 <= 0.001)
  }

  @Test def `test predictTemperature`(): Unit = milestoneTest {
    val grid = (gridLocation: GridLocation) => {
      //println("GridLocation = " + gridLocation)
      val map = Map(
        GridLocation(50, -163) -> 0.0,
        GridLocation(49, -163) -> 0.0,
        GridLocation(50, -162) -> 1.52587890625E-5,
        GridLocation(49, -162) -> 0.0
      )
      map(gridLocation)
    }
    val interpolatedTemperature = Visualization2.predictTemperature(Location(50.1, -163), grid)
    assert(interpolatedTemperature - 1.52587890625E-6 <= 0.001)
  }

  @Test def `test visualizeGrid`(): Unit = milestoneTest {
    val tile = Tile(0, 0, 0)
    val temperatures = Seq[(Location, Temperature)](
      (Location(45, -90), 32),
      (Location(45, 90), 90.0),
      (Location(-45, 90), 90.0),
      (Location(-45, -90), 32.0)
    )
    val gridFn = Manipulation.makeGrid(temperatures)
    val colors = Seq[(Temperature, Color)](
      (60, Color(255, 255, 255)),
      (32, Color(255, 0, 0)),
      (12, Color(255, 255, 0)),
      (0, Color(0, 255, 255)),
      (-15, Color(0, 0, 255)),
      (-27, Color(255, 0, 255)),
      (-50, Color(33, 0, 107)),
      (-60, Color(0, 0, 0))
    ).reverse

    val image = Visualization2.visualizeGrid(gridFn, colors, tile)
    println("HAHA = " + image)
    image.output(new java.io.File("visualization2_test.png"))
  }

  @Test def `test visualizeGrid for a year`(): Unit = milestoneTest {
    val year = 2015
    val locationYearlyAverageRecords = Seq(
        (year, Extraction.locationYearlyAverageRecords(
            Extraction.locateTemperatures(year, "/stations.csv", "/" + year + ".csv")
        ))
    )
    println("Completed extracting yearly average records: ", locationYearlyAverageRecords(0)._2.size)
    val grid = Manipulation.makeGrid(locationYearlyAverageRecords(0)._2)
    Visualization2.generateTiles(year, grid, Visualization2.generateImage)
  }*/
}

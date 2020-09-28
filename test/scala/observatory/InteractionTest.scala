package observatory

import scala.collection.concurrent.TrieMap
import org.junit.Assert._
import org.junit.Test
import java.time.LocalDate
import java.io.PrintWriter

trait InteractionTest extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("interactive visualization", 3) _

  /*@Test def `test tileLocation`(): Unit = milestoneTest {
    val tile = Tile(0, 0, 2)
    val location = Interaction.tileLocation(tile)
    println("HAHA = ", location)
    assert(location.lat - 85.051 <= 0.001)
    assert(location.lon + 180 <= 0.001)
  }

  @Test def `test tile`(): Unit = milestoneTest {
    val tile = Tile(0, 0, 0)
    val temperatures = Seq[(Location, Temperature)](
      (Location(45, -90), 32),
      (Location(45, 90), 90.0),
      (Location(-45, 90), 90.0),
      (Location(-45, -90), 32.0)
    )
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
    val image = Interaction.tile(temperatures, colors, tile)
    println("HAHA = ", image)
    image.output(new java.io.File("interaction_test.png"))
  }*/

  /*@Test def `test generateTiles`(): Unit = milestoneTest {
    val location2015AverageRecords = Extraction.locationYearlyAverageRecords(Seq(
      (LocalDate.of(2015, 8, 11), Location(37.35, -78.433), 27.3),
      (LocalDate.of(2015, 12, 6), Location(37.358, -78.438), 0.0),
      (LocalDate.of(2015, 1, 29), Location(37.358, -78.438), 2.0)
    ))
    val locationYearlyAverageRecords = Seq(
      (2015, location2015AverageRecords)
    )
    Interaction.generateTiles[Iterable[(Location, Temperature)]](locationYearlyAverageRecords, Interaction.generateImage)
  }*/

  /*@Test def `test generateTiles`(): Unit = milestoneTest {
    val year = 2015
    val locationYearlyAverageRecords = Seq(
        (year, Extraction.locationYearlyAverageRecords(
            Extraction.locateTemperatures(year, "/stations.csv", "/" + year + ".csv")
        ))
    )
    println("Completed extracting yearly average records: ", locationYearlyAverageRecords(0)._2.size)
    
    //new PrintWriter("locationYearlyAverageRecords.txt") { write("" + locationYearlyAverageRecords); close }
    /*val locationYearlyAverageRecords = Seq(
      (year, List.fill(12000)(Location(48.304,-114.264),7.7648401826484035))
    )*/
    Interaction.generateTiles[Iterable[(Location, Temperature)]](locationYearlyAverageRecords, Interaction.generateImage)
  }*/
}

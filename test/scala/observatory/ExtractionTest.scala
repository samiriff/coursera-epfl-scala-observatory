package observatory

import org.junit.Assert._
import org.junit.Test
import java.time.LocalDate

trait ExtractionTest extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("data extraction", 1) _

  // Implement tests for the methods of the `Extraction` object
  /*@Test def `test locateTemperatures`(): Unit = milestoneTest {
    val locateTemperatures = Extraction.locateTemperatures(1975, "/stations.csv", "/1975.csv")
    println(locateTemperatures.take(5))
    val expectedList = List(
      (LocalDate.of(1975, 1, 1), Location(39.189,-103.716) , -9.277777777777779), 
      (LocalDate.of(1975, 1, 2), Location(39.189,-103.716), -8.277777777777779), 
      (LocalDate.of(1975, 1, 3), Location(39.189,-103.716), -4.666666666666666), 
      (LocalDate.of(1975, 1, 4), Location(39.189,-103.716), -5.055555555555556), 
      (LocalDate.of(1975, 1, 5), Location(39.189,-103.716), -2.999999999999999)
    )
    assertSameElements(locateTemperatures.take(5).toList, expectedList)
  }*/

  /*@Test def `test locateTemperatures with dummy data`(): Unit = milestoneTest {
    val locateTemperatures = Extraction.locateTemperatures(2015, "/stationsTest.csv", "/temperaturesTest.csv")
    val expectedList = List(
      (LocalDate.of(2015, 12, 6), Location(37.358, -78.438), 0.0),
      (LocalDate.of(2015, 1, 29), Location(37.358, -78.438), 2.000000000000001),
      (LocalDate.of(2015, 8, 11), Location(37.35, -78.433), 27.299999999999997),
    )
    assertSameElements(locateTemperatures.toList, expectedList)
  }*/

  /*@Test def `test locationYearlyAverageRecords`(): Unit = milestoneTest {
    val locationYearlyAverageRecords = Extraction.locationYearlyAverageRecords(Seq(
      (LocalDate.of(2015, 8, 11), Location(37.35, -78.433), 27.3),
      (LocalDate.of(2015, 12, 6), Location(37.358, -78.438), 0.0),
      (LocalDate.of(2015, 1, 29), Location(37.358, -78.438), 2.0)
    ))
    val expectedList = List(
      (Location(37.35, -78.433), 27.3),
      (Location(37.358, -78.438), 1.0)
    )
    assertSameElements(locationYearlyAverageRecords.toList, expectedList)
  }*/

  /**
    * Asserts that all the elements in a given list and an expected list are the same,
    * regardless of order. For a prettier output, given and expected should be sorted
    * with the same ordering.
    * @param actual The actual list
    * @param expected The expected list
    * @tparam A Type of the list elements
    */
  def assertSameElements[A](actual: List[A], expected: List[A]): Unit = {
    val givenSet = actual.toSet
    val expectedSet = expected.toSet

    val unexpected = givenSet -- expectedSet
    val missing = expectedSet -- givenSet

    val noUnexpectedElements = unexpected.isEmpty
    val noMissingElements = missing.isEmpty

    val noMatchString =
      s"""
         |Expected: ${previewList(expected)}
         |Actual:   ${previewList(actual)}""".stripMargin

    assert(noUnexpectedElements,
      s"""|$noMatchString
          |The given collection contains some unexpected elements: ${previewList(unexpected.toList, 5)}""".stripMargin)

    assert(noMissingElements,
      s"""|$noMatchString
          |The given collection is missing some expected elements: ${previewList(missing.toList, 5)}""".stripMargin)
  }

  /**
    * Creates a truncated string representation of a list, adding ", ...)" if there
    * are too many elements to show
    * @param l The list to preview
    * @param n The number of elements to cut it at
    * @return A preview of the list, containing at most n elements.
    */
  def previewList[A](l: List[A], n: Int = 10): String =
    if (l.length <= n) l.toString
    else l.take(n).toString.dropRight(1) + ", ...)"
}

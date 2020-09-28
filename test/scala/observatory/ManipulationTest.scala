package observatory

import org.junit.Test

trait ManipulationTest extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("data manipulation", 4) _

  // Implement tests for methods of the `Manipulation` object
  /*@Test def `test makeGrid`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(-20, -20), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val makeGridFn = Manipulation.makeGrid(temperatures)
    val temperature = makeGridFn(GridLocation(0, 0))
    assert(scala.math.abs(temperature - 25) <= 0.001)
  }

  @Test def `test average`(): Unit = milestoneTest {
    val temperatures = Seq[(Location, Temperature)](
      (Location(-20, -20), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val yearlyTemperatures = List.fill(5)(temperatures)
    val averageFn = Manipulation.average(yearlyTemperatures)
    val temperature = averageFn(GridLocation(0, 0))
    assert(scala.math.abs(temperature - 25) <= 0.001)
  }

  @Test def `test deviation`(): Unit = milestoneTest {
    val oldTemperatures = Seq[(Location, Temperature)](
      (Location(-20, -20), 20),
      (Location(20, -20), 10),
      (Location(-20, 20), 30),
      (Location(20, 20), 40)
    )
    val yearlyTemperatures = List.fill(5)(oldTemperatures)
    val normals = Manipulation.average(yearlyTemperatures)
    
    val currentTemperatures = Seq[(Location, Temperature)](
      (Location(-20, -20), 30),
      (Location(20, -20), 20),
      (Location(-20, 20), 40),
      (Location(20, 20), 50)
    )
    val deviationFn = Manipulation.deviation(currentTemperatures, normals)
    val deviation = deviationFn(GridLocation(0, 0))
    assert(scala.math.abs(deviation - 10) <= 0.001)
  }*/
}

package observatory

import org.junit.Assert._
import org.junit.Test

trait Interaction2Test extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("interactive user interface", 6) _

  // Implement tests for methods of the `Interaction2` object
  @Test def `test yearBounds`(): Unit = milestoneTest {
    val availableLayers = Interaction2.availableLayers
    val selectedLayer = Var[Layer](availableLayers(0))
    val yearBounds = Interaction2.yearBounds(selectedLayer)
    assert(yearBounds().equals((1975 to 2015)))

    selectedLayer() = availableLayers(1)
    assert(yearBounds().equals((1991 to 2015)))
  }

  @Test def `test yearSelection`(): Unit = milestoneTest {
    val availableLayers = Interaction2.availableLayers
    val selectedLayer = Var[Layer](availableLayers(1))
    val sliderValue = Var[Year](1975)
    val yearSelection = Interaction2.yearSelection(selectedLayer, sliderValue)
    assert(yearSelection() == 1991)

    sliderValue() = 1933
    assert(yearSelection() == 1991)

    sliderValue() = 2020
    assert(yearSelection() == 2015)

    sliderValue() = 2000
    assert(yearSelection() == 2000)
  }

  @Test def `test layerUrlPattern`(): Unit = milestoneTest {
    val availableLayers = Interaction2.availableLayers
    val selectedLayer = Var[Layer](availableLayers(0))
    val selectedYear = Var[Year](2015)
    val layerUrlPattern = Interaction2.layerUrlPattern(selectedLayer, selectedYear)
    assert(layerUrlPattern().equals("target/temperatures/2015/{z}/{x}-{y}.png"))

    selectedYear() = 1975
    assert(layerUrlPattern().equals("target/temperatures/1975/{z}/{x}-{y}.png"))

    selectedLayer() = availableLayers(1)
    assert(layerUrlPattern().equals("target/deviations/1975/{z}/{x}-{y}.png"))
  }

  @Test def `test caption`(): Unit = milestoneTest {
    val availableLayers = Interaction2.availableLayers
    val selectedLayer = Var[Layer](availableLayers(0))
    val selectedYear = Var[Year](2015)
    val layerUrlPattern = Interaction2.caption(selectedLayer, selectedYear)
    assert(layerUrlPattern().equals("Temperatures (2015)"))

    selectedYear() = 1975
    assert(layerUrlPattern().equals("Temperatures (1975)"))

    selectedLayer() = availableLayers(1)
    assert(layerUrlPattern().equals("Deviations (1975)"))
  }
}

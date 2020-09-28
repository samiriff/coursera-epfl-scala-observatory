package observatory

import org.apache.log4j.{Level, Logger}

object Main extends App {
    //Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    println("HAHA")
    //println(Extraction.locateTemperatures(1975, "", ""))

    val colors = Seq[(Temperature, Color)](
        (60, Color(255, 255, 255)),
        (32, Color(255, 0, 0)),
        (12, Color(255, 255, 0)),
        (0, Color(0, 255, 255)),
        (-15, Color(0, 0, 255)),
        (-27, Color(255, 0, 255)),
        (-50, Color(33, 0, 107)),
        (-60, Color(0, 0, 0))
    )
    //println("1975 Yearly Average Records = ", locationYearlyAverageRecords)
    //val image = Visualization.visualize(locationYearlyAverageRecords, colors)
    //image.output(new java.io.File("1975_2.png"))

    val year = 2015
    val locationYearlyAverageRecords = Seq(
        (year, Extraction.locationYearlyAverageRecords(
            Extraction.locateTemperatures(year, "/stations.csv", "/" + year + ".csv")
        ))
    )
    println("Completed extracting yearly average records")
    Interaction.generateTiles[Iterable[(Location, Temperature)]](locationYearlyAverageRecords, Interaction.generateImage)
}

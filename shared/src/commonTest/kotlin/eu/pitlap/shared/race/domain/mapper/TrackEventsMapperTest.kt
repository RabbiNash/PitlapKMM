package eu.pitlap.shared.race.domain.mapper

import eu.pitlap.shared.core.data.models.ergast.tables.RaceTable
import eu.pitlap.shared.schedule.data.ergast.Circuit
import eu.pitlap.shared.schedule.data.ergast.Location
import eu.pitlap.shared.schedule.data.ergast.QualifyingResult
import eu.pitlap.shared.schedule.data.ergast.Race
import eu.pitlap.shared.schedule.data.ergast.RaceResult
import eu.pitlap.shared.standings.data.ergast.Constructor
import eu.pitlap.shared.standings.data.ergast.Driver
import kotlin.test.Test
import kotlin.test.assertEquals

class TrackEventsMapperTest {
    private val driver = Driver(
        driverId = "norris",
        permanentNumber = "4",
        code = "NOR",
        url = "https://example.test/norris",
        givenName = "Lando",
        familyName = "Norris",
        dateOfBirth = "1999-11-13",
        nationality = "British"
    )
    private val constructor = Constructor(
        constructorId = "mclaren",
        url = "https://example.test/mclaren",
        name = "McLaren",
        nationality = "British"
    )
    private val circuit = Circuit(
        circuitId = "albert_park",
        url = "https://example.test/albert-park",
        circuitName = "Albert Park",
        location = Location(locality = "Melbourne", country = "Australia")
    )

    @Test
    fun mapsErgastRaceResultsToTheExistingDomainModel() {
        val table = RaceTable(
            season = "2025",
            races = listOf(
                Race(
                    season = "2025",
                    round = "1",
                    url = "https://example.test/race",
                    raceName = "Australian Grand Prix",
                    circuit = circuit,
                    date = "2025-03-16",
                    time = "04:00:00Z",
                    results = listOf(
                        RaceResult("1", "1", "25", driver, constructor, "2", "Finished")
                    )
                )
            )
        )

        assertEquals(
            listOf("1", "25", "McLaren", "Lando Norris", "2", "1"),
            table.toRaceResultsModel().single().let {
                listOf(it.position.toString(), it.points.toString(), it.teamName, it.fullName, it.gridPosition.toString(), it.classifiedPosition)
            }
        )
    }

    @Test
    fun mapsErgastQualifyingResultsToTheExistingDomainModel() {
        val table = RaceTable(
            season = "2025",
            races = listOf(
                Race(
                    season = "2025",
                    round = "1",
                    url = "https://example.test/race",
                    raceName = "Australian Grand Prix",
                    circuit = circuit,
                    date = "2025-03-16",
                    time = "04:00:00Z",
                    qualifyingResults = listOf(
                        QualifyingResult("1", driver, constructor, "1:15.912", "1:15.415", "1:15.096")
                    )
                )
            )
        )

        val result = table.toQualifyingResultsModel().single()

        assertEquals(1, result.position)
        assertEquals("Lando Norris", result.fullName)
        assertEquals("McLaren", result.teamName)
        assertEquals("1:15.096", result.q3)
    }
}

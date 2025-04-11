package eu.pitlap.shared.modules

import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel
import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

interface PitlapService {
    suspend fun getDriverStandings(): List<DriverStandingModel>
    suspend fun getConstructorStandings(): List<ConstructorStandingModel>
    suspend fun getPracticeLaps(year: Int, round: Int, sessionName: String): List<GroupedLapModel>
    suspend fun getQualifyingResults(year: Int, round: Int): List<QualifyingResultModel>
    suspend fun getRaceResults(year: Int, round: Int): List<RaceResultModel>
    suspend fun getSchedule(year: Int): List<EventScheduleModel>
    suspend fun getRaceSummary(year: Int, round: Int): RaceSummaryModel
    suspend fun getTrackSummary(trackName: String): TrackSummaryModel
}

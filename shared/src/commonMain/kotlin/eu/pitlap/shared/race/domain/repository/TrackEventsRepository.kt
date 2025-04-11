package eu.pitlap.shared.race.domain.repository

import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel

interface TrackEventsRepository {
    @Throws(Throwable::class)
    suspend fun getPracticeLaps(year: Int, round: Int, sessionName: String): List<GroupedLapModel>

    @Throws(Throwable::class)
    suspend fun getQualifyingResults(year: Int, round: Int): List<QualifyingResultModel>

    @Throws(Throwable::class)
    suspend fun getRaceResults(year: Int, round: Int): List<RaceResultModel>
}

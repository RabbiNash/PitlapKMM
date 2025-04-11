package eu.pitlap.shared.race.domain.repository

import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel

internal interface SummaryRepository {
    @Throws(Throwable::class)
    suspend fun getRaceSummary(year: Int, round: Int): RaceSummaryModel

    @Throws(Throwable::class)
    suspend fun getTrackSummary(trackName: String): TrackSummaryModel
}

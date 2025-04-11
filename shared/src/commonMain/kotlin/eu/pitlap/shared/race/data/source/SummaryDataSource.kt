package eu.pitlap.shared.race.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.race.data.dto.RaceSummaryDto
import eu.pitlap.shared.race.data.dto.TrackSummaryDto

internal interface SummaryDataSource {
    suspend fun getRaceSummary(year: Int, round: Int): Result<RaceSummaryDto, ApiError.Remote>
    suspend fun getTrackSummary(trackName: String): Result<TrackSummaryDto, ApiError.Remote>
}

package eu.pitlap.shared.race.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.data.models.ergast.tables.RaceTable
import eu.pitlap.shared.race.data.dto.TopSpeedsDto
import eu.pitlap.shared.race.data.dto.PracticeLapsDto

internal interface TrackEventsDataSource {
    suspend fun getPracticeLaps(year: Int, round: Int, sessionName: String): Result<PracticeLapsDto, ApiError.Remote>
    suspend fun getQualifyingResults(year: Int, round: Int): Result<RaceTable, ApiError.Remote>
    suspend fun getRaceResults(year: Int, round: Int): Result<RaceTable, ApiError.Remote>
    suspend fun getTopSpeeds(year: Int, round: Int, sessionName: String): Result<TopSpeedsDto, ApiError.Remote>
}

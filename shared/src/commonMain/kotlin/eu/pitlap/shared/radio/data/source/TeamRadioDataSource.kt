package eu.pitlap.shared.radio.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.radio.data.dto.TeamRadioDto

internal interface TeamRadioDataSource {
    suspend fun getLatestTeamRadio(driverNumber: Int): Result<List<TeamRadioDto>, ApiError.Remote>
}

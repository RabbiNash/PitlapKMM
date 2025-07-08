package eu.pitlap.shared.radio.domain.repository

import eu.pitlap.shared.radio.domain.model.TeamRadioModel

internal interface TeamRadioRepository {
    @Throws(Throwable::class)
    suspend fun getLatestTeamRadio(driverNumber: Int): List<TeamRadioModel>
}

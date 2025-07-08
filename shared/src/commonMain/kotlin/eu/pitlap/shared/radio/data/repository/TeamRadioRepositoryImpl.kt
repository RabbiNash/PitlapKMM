package eu.pitlap.shared.radio.data.repository

import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.radio.data.source.TeamRadioDataSource
import eu.pitlap.shared.radio.data.source.TeamRadioDataSourceImpl
import eu.pitlap.shared.radio.domain.mapper.toDomainModel
import eu.pitlap.shared.radio.domain.model.TeamRadioModel
import eu.pitlap.shared.radio.domain.repository.TeamRadioRepository

internal class TeamRadioRepositoryImpl(
    private val dataSource: TeamRadioDataSource = TeamRadioDataSourceImpl()
): TeamRadioRepository {
    override suspend fun getLatestTeamRadio(driverNumber: Int): List<TeamRadioModel> {
        return when(val result = dataSource.getLatestTeamRadio(driverNumber)) {
            is Result.Success -> result.data.map {
                it.toDomainModel()
            }
            is Result.Error -> throw result.error.toThrowable()
        }
    }
}

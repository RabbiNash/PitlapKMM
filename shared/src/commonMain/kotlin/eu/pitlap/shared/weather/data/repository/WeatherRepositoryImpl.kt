package eu.pitlap.shared.weather.data.repository

import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.weather.data.source.WeatherDataSource
import eu.pitlap.shared.weather.data.source.WeatherDataSourceImpl
import eu.pitlap.shared.weather.domain.mapper.toModel
import eu.pitlap.shared.weather.domain.model.WeatherModel
import eu.pitlap.shared.weather.domain.repository.WeatherRepository

internal class WeatherRepositoryImpl(
    private val dataSource: WeatherDataSource = WeatherDataSourceImpl()
): WeatherRepository {
    override suspend fun getWeatherSummary(year: Int, round: Int): WeatherModel {
        return when(val result = dataSource.getWeather(year, round)) {
            is Result.Success -> result.data.toModel()
            is Result.Error -> throw result.error.toThrowable()
        }
    }
}

package eu.pitlap.shared.schedule.data.repository

import eu.pitlap.shared.cache.Database
import eu.pitlap.shared.cache.DatabaseProvider
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.schedule.data.source.ScheduleDataSource
import eu.pitlap.shared.schedule.data.source.ScheduleDataSourceImpl
import eu.pitlap.shared.schedule.domain.mapper.toEventModel
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.domain.repository.ScheduleRepository

internal class ScheduleRepositoryImpl(
    private val dataSource: ScheduleDataSource = ScheduleDataSourceImpl(),
    private val database: Database = DatabaseProvider.get()
): ScheduleRepository {
    override suspend fun getSchedule(year: Int, forceRefresh: Boolean): List<EventScheduleModel> {
        val cachedEvents = database.getEventByYear(year = year.toLong())
        return if (cachedEvents.isNotEmpty() && !forceRefresh) {
            cachedEvents
        } else {
            when(val result = dataSource.getEventSchedule(year)) {
                is Result.Success -> {
                    result.data.map { it.toEventModel() }.also {
                        database.clearAndCreateEvents(it)
                    }
                }
                is Result.Error -> throw result.error.toThrowable()
            }
        }
    }

    override suspend fun getEvent(year: Int, round: Int): EventScheduleModel? {
        return database.getEventByYearAndRound(year.toLong(), round.toLong())
    }
}

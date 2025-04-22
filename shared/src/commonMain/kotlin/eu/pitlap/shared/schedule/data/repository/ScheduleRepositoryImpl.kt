package eu.pitlap.shared.schedule.data.repository

import eu.pitlap.shared.cache.dao.event.EventDAO
import eu.pitlap.shared.cache.factory.DatabaseProvider
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.schedule.data.source.ScheduleDataSource
import eu.pitlap.shared.schedule.data.source.ScheduleDataSourceImpl
import eu.pitlap.shared.schedule.domain.mapper.toEventModel
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.domain.repository.ScheduleRepository

internal class ScheduleRepositoryImpl(
    private val dataSource: ScheduleDataSource = ScheduleDataSourceImpl(),
    private val dao: EventDAO = DatabaseProvider.getEventDAO()
): ScheduleRepository {
    override suspend fun getSchedule(year: Int, forceRefresh: Boolean): List<EventScheduleModel> {
        val cachedEvents = dao.getEventByYear(year = year.toLong())
        return if (cachedEvents.isNotEmpty() && !forceRefresh) {
            cachedEvents
        } else {
            when(val result = dataSource.getEventSchedule(year)) {
                is Result.Success -> {
                    result.data.map { it.toEventModel() }.also {
                        dao.clearAndCreateEvents(it)
                    }
                }
                is Result.Error -> {
                    throw result.error.toThrowable()
                }
            }
        }
    }

    override suspend fun getEvent(year: Int, round: Int): EventScheduleModel? {
        return dao.getEventByYearAndRound(year.toLong(), round.toLong())
    }

    override suspend fun getNextEvent(): EventScheduleModel? {
        return dao.getNextScheduledEvent()
    }
}

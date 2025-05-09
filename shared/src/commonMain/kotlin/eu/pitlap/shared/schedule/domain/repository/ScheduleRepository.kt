package eu.pitlap.shared.schedule.domain.repository

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

internal interface ScheduleRepository {
    @Throws(Throwable::class)
    suspend fun getSchedule(year: Int, forceRefresh: Boolean = false): List<EventScheduleModel>

    @Throws(Throwable::class)
    suspend fun getEvent(year: Int, round: Int): EventScheduleModel?

    @Throws(Throwable::class)
    suspend fun getNextEvent(): EventScheduleModel?
}

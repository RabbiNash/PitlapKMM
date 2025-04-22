package eu.pitlap.shared.cache.dao.event

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

internal interface EventDAO {
    fun getAllEvents(): List<EventScheduleModel>
    fun getEventByYearAndRound(year: Long, round: Long): EventScheduleModel?
    fun getEventByYear(year: Long): List<EventScheduleModel>
    fun getNextScheduledEvent(): EventScheduleModel?
    fun clearAndCreateEvents(events: List<EventScheduleModel>)
    fun insertEvent(event: EventScheduleModel)
}

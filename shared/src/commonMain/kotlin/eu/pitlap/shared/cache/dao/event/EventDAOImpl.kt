package eu.pitlap.shared.cache.dao.event

import eu.pitlap.shared.cache.PitlapDBQueries
import eu.pitlap.shared.cache.mapper.EventMapper
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

internal class EventDAOImpl(
    private val eventQueries: PitlapDBQueries,
): EventDAO {
    override fun getAllEvents(): List<EventScheduleModel> =
        eventQueries.selectAllEventsInfo(EventMapper::mapToEventScheduleModel).executeAsList()

    override fun getEventByYearAndRound(year: Long, round: Long): EventScheduleModel? =
        eventQueries.selectEventByYearAndRound(year.toString(), round, EventMapper::mapToEventScheduleModel).executeAsOneOrNull()

    override fun getEventByYear(year: Long): List<EventScheduleModel> =
        eventQueries.selectEventByYear(year.toString(), EventMapper::mapToEventScheduleModel).executeAsList()

    override fun getNextScheduledEvent(): EventScheduleModel? {
        return eventQueries.selectNextUpcomingEvent(EventMapper::mapToEventScheduleModel).executeAsOneOrNull()
    }

    override fun clearAndCreateEvents(events: List<EventScheduleModel>) {
        eventQueries.removeAllEVents()
        events.forEach { insertEvent(it) }
    }

    override fun insertEvent(event: EventScheduleModel) {
        eventQueries.insertEvent(
            round = event.round.toLong(),
            country = event.country,
            officialEventName = event.officialEventName,
            eventName = event.eventName,
            eventFormat = event.eventFormat,
            session1 = event.session1,
            session1DateUTC = event.session1DateUTC,
            session2 = event.session2,
            session2DateUTC = event.session2DateUTC,
            session3 = event.session3,
            session3DateUTC = event.session3DateUTC,
            session4 = event.session4,
            session4DateUTC = event.session4DateUTC,
            session5 = event.session5,
            session5DateUTC = event.session5DateUTC,
            year = event.year,
        )
    }
}

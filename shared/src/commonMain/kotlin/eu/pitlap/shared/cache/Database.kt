package eu.pitlap.shared.cache

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = PitlapDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.pitlapDBQueries

    internal fun getAllEvents(): List<EventScheduleModel> {
        return dbQuery.selectAllEventsInfo(::mapToEventScheduleModel).executeAsList()
    }

    internal fun getEventByYearAndRound(year: Long, round: Long): EventScheduleModel? {
        return dbQuery.selectEventByYearAndRound(year.toString(), round, ::mapToEventScheduleModel).executeAsOneOrNull()
    }

    internal fun getEventByYear(year: Long): List<EventScheduleModel> {
        return dbQuery.selectEventByYear(year.toString(), ::mapToEventScheduleModel).executeAsList()
    }

    private fun mapToEventScheduleModel(
        round: Long,
        country: String,
        officialEventName: String,
        eventName: String,
        eventFormat: String,
        session1: String,
        session1DateUTC: String?,
        session2: String,
        session2DateUTC: String?,
        session3: String,
        session3DateUTC: String?,
        session4: String,
        session4DateUTC: String?,
        session5: String,
        session5DateUTC: String?,
        year: String,
    ): EventScheduleModel {
        return EventScheduleModel(
            round = round.toInt(),
            country = country,
            officialEventName = officialEventName,
            eventName = eventName,
            eventFormat = eventFormat,
            session1 = session1,
            session1DateUTC = session1DateUTC,
            session2 = session2,
            session2DateUTC = session2DateUTC,
            session3 = session3,
            session3DateUTC = session3DateUTC,
            session4 = session4,
            session4DateUTC = session4DateUTC,
            session5 = session5,
            session5DateUTC = session5DateUTC,
            year = year,
        )
    }

    internal fun clearAndCreateEvents(events: List<EventScheduleModel>) {
        dbQuery.transaction {
            dbQuery.removeAllEVents()
            events.forEach { event ->
                dbQuery.insertEvent(
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
    }
}

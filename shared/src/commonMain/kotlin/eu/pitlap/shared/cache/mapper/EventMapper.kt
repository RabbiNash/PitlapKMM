package eu.pitlap.shared.cache.mapper

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

object EventMapper {
    fun mapToEventScheduleModel(
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
    ): EventScheduleModel = EventScheduleModel(
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

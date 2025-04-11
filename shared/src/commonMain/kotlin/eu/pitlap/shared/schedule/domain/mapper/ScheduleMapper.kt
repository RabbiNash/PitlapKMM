package eu.pitlap.shared.schedule.domain.mapper

import eu.pitlap.shared.schedule.data.dto.EventScheduleDto
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

internal fun EventScheduleDto.toEventModel(): EventScheduleModel {
    return EventScheduleModel(
        round = round,
        country = country,
        officialEventName = officialEventName,
        eventName = eventName,
        eventFormat = eventFormat.value,
        session1 = session1.value,
        session1DateUTC = session1DateUTC,
        session2 = session2.value,
        session2DateUTC = session2DateUTC,
        session3 = session3.value,
        session3DateUTC = session3DateUTC,
        session4 = session4.value,
        session4DateUTC = session4DateUTC,
        session5 = session5.value,
        session5DateUTC = session5DateUTC,
        year = year
    )
}


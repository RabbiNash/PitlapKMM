package eu.pitlap.shared.schedule.data.mapper

import eu.pitlap.shared.schedule.data.dto.EventFormatModel
import eu.pitlap.shared.schedule.data.dto.EventScheduleDto
import eu.pitlap.shared.schedule.data.dto.Session1
import eu.pitlap.shared.schedule.data.dto.Session2
import eu.pitlap.shared.schedule.data.dto.Session3
import eu.pitlap.shared.schedule.data.dto.Session4
import eu.pitlap.shared.schedule.data.dto.Session5
import eu.pitlap.shared.schedule.data.ergast.Race

internal fun List<Race>.toEventSchedule(): List<EventScheduleDto> {
    return this.map { race ->
        val session1 = race.firstPractice?.let { Session1.PRACTICE_1 } ?: Session1.PRACTICE_1
        val session2 = when {
            race.secondPractice != null -> Session2.PRACTICE_2
            race.sprintQualifying != null -> Session2.SPRINT_QUALIFYING
            else -> Session2.PRACTICE_2
        }
        val session3 = when {
            race.thirdPractice != null -> Session3.PRACTICE_3
            race.sprint != null -> Session3.SPRINT
            else -> Session3.PRACTICE_3
        }
        val session4 = race.qualifying?.let { Session4.QUALIFYING } ?: Session4.NONE
        val session5 = Session5.RACE // All races have a race

        val session1UTC = race.firstPractice?.let { "${it.date}T${it.time}" } ?: ""
        val session2UTC = race.secondPractice?.let { "${it.date}T${it.time}" }
            ?: race.sprintQualifying?.let { "${it.date}T${it.time}" } ?: ""
        val session3UTC = race.thirdPractice?.let { "${it.date}T${it.time}" }
            ?: race.sprint?.let { "${it.date}T${it.time}" } ?: ""
        val session4UTC = race.qualifying?.let { "${it.date}T${it.time}" } ?: ""
        val session5UTC = "${race.date}T${race.time}"

        val eventFormat = when {
            race.sprintQualifying != null -> EventFormatModel.SPRINT_QUALIFYING
            race.sprint != null -> EventFormatModel.SPRINT
            else -> EventFormatModel.CONVENTIONAL
        }

        EventScheduleDto(
            round = race.round.toInt(),
            country = race.circuit.location.country,
            officialEventName = race.raceName,
            eventName = race.circuit.circuitName,
            eventFormat = eventFormat,
            session1 = session1,
            session1DateUTC = session1UTC,
            session2 = session2,
            session2DateUTC = session2UTC,
            session3 = session3,
            session3DateUTC = session3UTC,
            session4 = session4,
            session4DateUTC = session4UTC,
            session5 = session5,
            session5DateUTC = session5UTC,
            year = race.season
        )
    }
}

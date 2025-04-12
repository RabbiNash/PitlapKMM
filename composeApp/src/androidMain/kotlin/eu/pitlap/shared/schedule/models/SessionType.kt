package eu.pitlap.shared.schedule.models

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.utils.DateUtils

enum class SessionType(val displayName: String) {
    SESSION1("Session 1"),
    SESSION2("Session 2"),
    SESSION3("Session 3"),
    SESSION4("Session 4"),
    SESSION5("Session 5");

    val id: Int
        get() = ordinal
}

fun EventScheduleModel.sessionTime(type: SessionType): String? {
    return when (type) {
        SessionType.SESSION1 -> session1DateUTC
        SessionType.SESSION2 -> session2DateUTC
        SessionType.SESSION3 -> session3DateUTC
        SessionType.SESSION4 -> session4DateUTC
        SessionType.SESSION5 -> session5DateUTC
    }
}

fun EventScheduleModel.sessionName(type: SessionType): String {
    return when (type) {
        SessionType.SESSION1 -> session1
        SessionType.SESSION2 -> session2
        SessionType.SESSION3 -> session3
        SessionType.SESSION4 -> session4
        SessionType.SESSION5 -> session5
    }
}


package eu.pitlap.shared.schedule.domain.model

data class EventScheduleModel(
    val round: Int,
    val country: String,
    val officialEventName: String,
    val eventName: String,
    val eventFormat: String,
    val session1: String,
    val session1DateUTC: String?,
    val session2: String,
    val session2DateUTC: String?,
    val session3: String,
    val session3DateUTC: String?,
    val session4: String,
    val session4DateUTC: String?,
    val session5: String,
    val session5DateUTC: String?,
    val year: String,
)

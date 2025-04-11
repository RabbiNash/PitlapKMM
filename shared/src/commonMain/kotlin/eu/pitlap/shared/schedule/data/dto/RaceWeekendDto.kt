package eu.pitlap.shared.schedule.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EventScheduleDto(
    val round: Int,
    val country: String,
    val officialEventName: String,
    val eventName: String,
    val eventFormat: EventFormatModel,
    val session1: Session1,
    @SerialName("session1DateUtc")
    val session1DateUTC: String,
    val session2: Session2,
    @SerialName("session2DateUtc")
    val session2DateUTC: String,
    val session3: Session3,
    @SerialName("session3DateUtc")
    val session3DateUTC: String,
    val session4: Session4,
    @SerialName("session4DateUtc")
    val session4DateUTC: String,
    val session5: Session5,
    @SerialName("session5DateUtc")
    val session5DateUTC: String,
    val year: String,
)

@Serializable
enum class Session1(val value: String) {
    @SerialName("Practice 1")
    PRACTICE_1("Practice 1")
}

@Serializable
enum class Session2(val value: String) {
    @SerialName("Practice 2")
    PRACTICE_2("Practice 2"),

    @SerialName("Sprint Qualifying")
    SPRINT_QUALIFYING("Sprint Qualifying")
}

@Serializable
enum class Session3(val value: String) {
    @SerialName("Practice 3")
    PRACTICE_3("Practice 3"),

    @SerialName("Sprint")
    SPRINT("Sprint")
}

@Serializable
enum class Session4(val value: String) {
    @SerialName("None")
    NONE("None"),

    @SerialName("Qualifying")
    QUALIFYING("Qualifying")
}

@Serializable
enum class Session5(val value: String) {
    @SerialName("None")
    NONE("None"),

    @SerialName("Race")
    RACE("Race")
}

@Serializable
enum class EventFormatModel(val value: String) {
    @SerialName("conventional")
    CONVENTIONAL("conventional"),

    @SerialName("Sprint")
    SPRINT("Sprint"),

    @SerialName("sprint_qualifying")
    SPRINT_QUALIFYING("sprint_qualifying"),

    @SerialName("testing")
    TESTING("testing")
}

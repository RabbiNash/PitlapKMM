package eu.pitlap.shared.schedule.data.ergast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class Race(
    val season: String,
    val round: String,
    val url: String,
    val raceName: String,
    @SerialName("Circuit")
    val circuit: Circuit,
    val date: String,
    val time: String,
    @SerialName("FirstPractice") val firstPractice: Session? = null,
    @SerialName("SecondPractice") val secondPractice: Session? = null,
    @SerialName("ThirdPractice") val thirdPractice: Session? = null,
    @SerialName("Qualifying")
    val qualifying: Session? = null,
    @SerialName("Sprint")
    val sprint: Session? = null,
    @SerialName("SprintQualifying")
    val sprintQualifying: Session? = null
)

@kotlinx.serialization.Serializable
data class Circuit(
    val circuitId: String,
    val url: String,
    val circuitName: String,
    @SerialName("Location")
    val location: Location
)

@kotlinx.serialization.Serializable
data class Location(
    val lat: String? = null,
    val long: String? = null,
    val locality: String,
    val country: String
)

@Serializable
data class Session(
    val date: String,
    val time: String
)
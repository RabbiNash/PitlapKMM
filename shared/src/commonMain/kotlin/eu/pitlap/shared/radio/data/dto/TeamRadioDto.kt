package eu.pitlap.shared.radio.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TeamRadioDto(
    @SerialName("date")
    val date: String,
    @SerialName("driver_number")
    val driverNumber: Int,
    @SerialName("meeting_key")
    val meetingKey: Int,
    @SerialName("recording_url")
    val recordingUrl: String,
    @SerialName("session_key")
    val sessionKey: Int
)

package eu.pitlap.shared.radio.domain.model

data class TeamRadioModel(
    val date: String,
    val driverNumber: Int,
    val meetingKey: Int,
    val recordingUrl: String,
    val sessionKey: Int
)
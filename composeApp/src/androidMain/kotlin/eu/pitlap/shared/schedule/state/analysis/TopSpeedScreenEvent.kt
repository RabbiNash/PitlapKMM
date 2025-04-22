package eu.pitlap.shared.schedule.state.analysis

sealed class TopSpeedScreenEvent {
    data class LoadTopSpeeds(
        val year: Int,
        val round: Int,
        val sessionName: String
    ) : TopSpeedScreenEvent()
}

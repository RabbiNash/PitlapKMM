package eu.pitlap.shared.schedule.state

sealed class EventDetailScreenEvent {
    data class LoadEvent(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadRaceSummary(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadTrackFacts(val trackName: String) : EventDetailScreenEvent()
    data class LoadWeather(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadSessionDetail(
        val year: Int,
        val round: Int,
        val sessionName: String
    ) : EventDetailScreenEvent()
}

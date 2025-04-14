package eu.pitlap.shared.schedule.state

sealed class EventDetailScreenEvent {
    data class LoadEvent(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadRaceSummary(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadQualifying(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadRaceResult(val year: Int, val round: Int) : EventDetailScreenEvent()
    data class LoadPracticeLaps(
        val year: Int,
        val round: Int,
        val sessionName: String
    ) : EventDetailScreenEvent()
}

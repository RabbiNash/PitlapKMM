package eu.pitlap.shared.schedule.state

sealed class EventDetailScreenEvent {
    data class LoadEvent(val year: Int, val round: Int) : EventDetailScreenEvent()
}

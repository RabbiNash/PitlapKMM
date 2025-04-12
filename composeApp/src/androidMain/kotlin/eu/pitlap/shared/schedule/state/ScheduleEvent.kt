package eu.pitlap.shared.schedule.state

sealed class ScheduleScreenEvent {
    data class LoadSchedule(val year: Int, val showPastEvents: Boolean = false) : ScheduleScreenEvent()
    data class TogglePastEvents(val showPastEvents: Boolean) : ScheduleScreenEvent()
}

package eu.pitlap.shared.schedule.state

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

data class ScheduleScreenState(
    val isLoading: Boolean = false,
    val seasonCalendar: List<EventScheduleModel> = emptyList(),
    val nextSession: EventScheduleModel? = null,
    val showPastEvents: Boolean = false,
    val error: String? = null
)

package eu.pitlap.shared.schedule.state

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel

data class EventDetailScreenState(
    val isLoading: Boolean = false,
    val event: EventScheduleModel? = null,
    val trackSummary: String? = null,
    val raceSummary: String? = null,
    val error: String? = null
)

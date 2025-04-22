package eu.pitlap.shared.schedule.state

import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.weather.domain.model.WeatherModel

data class EventDetailScreenState(
    val isLoading: Boolean = false,
    val event: EventScheduleModel? = null,
    val trackSummary: String? = null,
    val raceSummary: String? = null,
    val weather: WeatherModel? = null,
    val error: String? = null
)

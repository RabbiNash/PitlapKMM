package eu.pitlap.shared.schedule.state.analysis

import eu.pitlap.shared.race.domain.model.TopSpeedModel

data class TopSpeedScreenState(
    val isLoading: Boolean = false,
    val results: List<TopSpeedModel> = emptyList(),
    val error: String? = null
)

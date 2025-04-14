package eu.pitlap.shared.schedule.state.race

import eu.pitlap.shared.race.domain.model.RaceResultModel

data class RaceScreenState(
    val isLoading: Boolean = false,
    val results: List<RaceResultModel> = emptyList(),
    val error: String? = null
)

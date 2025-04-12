package eu.pitlap.shared.schedule.state.practice

import eu.pitlap.shared.race.domain.model.GroupedLapModel

data class PracticeScreenState(
    val isLoading: Boolean = false,
    val results: List<GroupedLapModel> = emptyList(),
    val error: String? = null
)

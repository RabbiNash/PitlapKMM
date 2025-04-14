package eu.pitlap.shared.schedule.state.qualifying

import eu.pitlap.shared.race.domain.model.QualifyingResultModel

data class QualifyingScreenState(
    val isLoading: Boolean = false,
    val results: List<QualifyingResultModel> = emptyList(),
    val error: String? = null
)

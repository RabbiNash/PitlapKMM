package eu.pitlap.shared.standings.state

import eu.pitlap.shared.standings.models.StandingRowUiModel

data class StandingListScreenState(
    val isLoading: Boolean = false,
    val standings: List<StandingRowUiModel> = emptyList(),
    val error: String? = null
)

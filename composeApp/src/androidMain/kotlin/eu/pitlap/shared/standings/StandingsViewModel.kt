package eu.pitlap.shared.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.standings.models.StandingRowUiModel
import eu.pitlap.shared.standings.state.StandingListEvent
import eu.pitlap.shared.standings.state.StandingListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StandingsViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(StandingListScreenState())
    val state: StateFlow<StandingListScreenState> = _state.asStateFlow()

    fun onEvent(event: StandingListEvent) {
        when (event) {
            StandingListEvent.LoadDriverStanding -> loadStandings(
                fetch = { pitlapService.getDriverStandings() },
                mapToUi = {
                    StandingRowUiModel(
                        position = it.positionText,
                        fullName = "${it.givenName} ${it.familyName}",
                        constructorName = it.constructorName,
                        points = it.points.toString(),
                        wins = it.wins.toString()
                    )
                }
            )

            StandingListEvent.LoadConstructorStanding -> loadStandings(
                fetch = { pitlapService.getConstructorStandings() },
                mapToUi = {
                    StandingRowUiModel(
                        position = it.positionText,
                        fullName = null,
                        constructorName = it.constructorName,
                        points = it.points.toString(),
                        wins = it.wins.toString()
                    )
                }
            )
        }
    }

    private fun <T> loadStandings(
        fetch: suspend () -> List<T>,
        mapToUi: (T) -> StandingRowUiModel
    ) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val standings = fetch().map(mapToUi)
                _state.update { it.copy(isLoading = false, standings = standings) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}

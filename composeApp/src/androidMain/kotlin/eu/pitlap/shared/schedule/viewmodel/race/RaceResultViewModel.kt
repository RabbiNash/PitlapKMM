package eu.pitlap.shared.schedule.viewmodel.race

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.state.race.RaceScreenEvent
import eu.pitlap.shared.schedule.state.race.RaceScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RaceResultViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(RaceScreenState())
    val state: StateFlow<RaceScreenState> = _state.asStateFlow()

    fun onEvent(event: RaceScreenEvent) {
        when (event) {
            is RaceScreenEvent.LoadResults -> loadRaceResult(event.year, event.round)
        }
    }

    private fun loadRaceResult(year: Int, round: Int) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val results = pitlapService.getRaceResults(year, round)

                _state.update {
                    it.copy(
                        isLoading = false,
                        results = results
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}

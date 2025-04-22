package eu.pitlap.shared.schedule.viewmodel.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.state.analysis.TopSpeedScreenEvent
import eu.pitlap.shared.schedule.state.analysis.TopSpeedScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TopSpeedViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(TopSpeedScreenState())
    val state: StateFlow<TopSpeedScreenState> = _state.asStateFlow()

    fun onEvent(event: TopSpeedScreenEvent) {
        when (event) {
            is TopSpeedScreenEvent.LoadTopSpeeds -> loadTopSpeeds(event.year, event.round, event.sessionName)
        }
    }

    private fun loadTopSpeeds(year: Int, round: Int, sessionName: String) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val results = pitlapService.getSessionTopSpeeds(year, round, sessionName)

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


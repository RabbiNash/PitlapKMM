package eu.pitlap.shared.schedule.viewmodel.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.state.practice.PracticeScreenEvent
import eu.pitlap.shared.schedule.state.practice.PracticeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PracticeResultViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(PracticeScreenState())
    val state: StateFlow<PracticeScreenState> = _state.asStateFlow()

    fun onEvent(event: PracticeScreenEvent) {
        when (event) {
            is PracticeScreenEvent.LoadResults -> loadPracticeResult(event.year, event.round, event.sessionName)
        }
    }

    private fun loadPracticeResult(year: Int, round: Int, sessionName: String) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val results = pitlapService.getPracticeLaps(year, round, sessionName)

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

package eu.pitlap.shared.schedule.viewmodel.qualifying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.state.qualifying.QualifyingScreenEvent
import eu.pitlap.shared.schedule.state.qualifying.QualifyingScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QualifyingResultViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(QualifyingScreenState())
    val state: StateFlow<QualifyingScreenState> = _state.asStateFlow()

    fun onEvent(event: QualifyingScreenEvent) {
        when (event) {
            is QualifyingScreenEvent.LoadResults -> loadQualifyingResult(event.year, event.round)
        }
    }

    private fun loadQualifyingResult(year: Int, round: Int) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val results = pitlapService.getQualifyingResults(year, round)

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

package eu.pitlap.shared.schedule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.state.EventDetailScreenEvent
import eu.pitlap.shared.schedule.state.EventDetailScreenState
import eu.pitlap.shared.schedule.state.ScheduleScreenEvent
import eu.pitlap.shared.schedule.state.ScheduleScreenState
import eu.pitlap.shared.utils.DateUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(EventDetailScreenState())
    val state: StateFlow<EventDetailScreenState> = _state.asStateFlow()

    fun onEvent(event: EventDetailScreenEvent) {
        when (event) {
            is EventDetailScreenEvent.LoadEvent -> loadSchedule(event.year, event.round)
        }
    }

    private fun loadSchedule(year: Int, round: Int) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val event = pitlapService.getEvent(year, round)

                _state.update {
                    it.copy(
                        isLoading = false,
                        event = event
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}

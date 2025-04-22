package eu.pitlap.shared.schedule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.state.ScheduleScreenEvent
import eu.pitlap.shared.schedule.state.ScheduleScreenState
import eu.pitlap.shared.utils.DateUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class ScheduleViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleScreenState())
    val state: StateFlow<ScheduleScreenState> = _state.asStateFlow()

    fun onEvent(event: ScheduleScreenEvent) {
        when (event) {
            is ScheduleScreenEvent.LoadSchedule -> loadSchedule(event.year, event.showPastEvents)
            is ScheduleScreenEvent.TogglePastEvents -> loadSchedule(showPastEvents = event.showPastEvents)
        }
    }

    private fun loadSchedule(year: Int = 2025, showPastEvents: Boolean) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val schedule = pitlapService.getSchedule(year,)
                val filtered = if (showPastEvents) schedule.filter { !isNextEvent(it) }.reversed() else schedule.filter { isNextEvent(it) }
                val next = schedule.firstOrNull { isNextEvent(it) }

                _state.update {
                    it.copy(
                        isLoading = false,
                        seasonCalendar = filtered,
                        nextSession = next,
                        showPastEvents = showPastEvents
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun isNextEvent(event: EventScheduleModel): Boolean {
        val now = Date()
        val sessionDate = if (event.eventFormat == "conventional") {
            DateUtils.getDateFromString(event.session5DateUTC ?: return false)
        } else {
            DateUtils.getDateFromString(event.session3DateUTC ?: return false)
        }
        return sessionDate?.after(now) == true
    }
}

package eu.pitlap.shared.schedule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.schedule.state.EventDetailScreenEvent
import eu.pitlap.shared.schedule.state.EventDetailScreenState
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
            is EventDetailScreenEvent.LoadEvent -> fetchEventData(event.year, event.round)
            is EventDetailScreenEvent.LoadRaceSummary -> fetchRaceSummary(event.year, event.round)
            is EventDetailScreenEvent.LoadWeather -> fetchWeather(event.year, event.round)
            else -> Unit
        }
    }

    private fun fetchEventData(year: Int, round: Int) {
        updateLoadingState()

        viewModelScope.launch {
            val eventResult = runCatching { pitlapService.getEvent(year, round) }
            val weatherResult = runCatching { pitlapService.getWeather(year, round) }

            eventResult.onSuccess { event ->
                _state.update { it.copy(event = event) }
                fetchTrackFacts(event?.eventName ?: "")
            }

            weatherResult.onSuccess {
                _state.update { it.copy(weather = it.weather) }
            }

            val errorMessage = eventResult.exceptionOrNull()?.message

            _state.update { it.copy(isLoading = false, error = errorMessage) }
        }
    }

    private fun fetchRaceSummary(year: Int, round: Int) {
        updateLoadingState()

        viewModelScope.launch {
            runCatching {
                pitlapService.getRaceSummary(year, round)
            }.onSuccess { summary ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        raceSummary = summary.summary
                    )
                }
            }.onFailure { e ->
                _state.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private fun fetchTrackFacts(trackName: String) {
        updateLoadingState()

        viewModelScope.launch {
            runCatching {
                pitlapService.getTrackSummary(trackName)
            }.onSuccess { summary ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        trackSummary = summary.fact
                    )
                }
            }.onFailure { e ->
                _state.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private fun fetchWeather(year: Int, round: Int) {
        updateLoadingState()

        viewModelScope.launch {
            runCatching {
                pitlapService.getWeather(year, round)
            }.onSuccess { weather ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        weather = weather
                    )
                }
            }.onFailure { e ->
                _state.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private fun updateLoadingState() {
        _state.update { it.copy(isLoading = true, error = null) }
    }
}


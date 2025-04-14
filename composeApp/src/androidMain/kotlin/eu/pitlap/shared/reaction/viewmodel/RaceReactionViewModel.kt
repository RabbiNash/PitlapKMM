package eu.pitlap.shared.reaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.reaction.state.ReactionEvent
import eu.pitlap.shared.reaction.state.ReactionState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RaceReactionViewModel : ViewModel() {

    private val _state = MutableStateFlow(ReactionState())
    val state: StateFlow<ReactionState> = _state.asStateFlow()

    private var startTime: Long? = null
    private var sequenceJob: Job? = null

    fun onEvent(event: ReactionEvent) {
        when (event) {
            is ReactionEvent.ButtonPressed -> handleButtonPress()
        }
    }

    private fun handleButtonPress() {
        val currentState = _state.value

        if (currentState.isSequenceRunning) {
            if (startTime == null) {
                // Jump start
                _state.update {
                    it.copy(
                        isJumpStart = true,
                        activeIndex = -1,
                        isSequenceRunning = false
                    )
                }
                stopSequence()
            } else {
                stopTimer()
            }
        } else {
            _state.update { it.copy(isJumpStart = false) }
            sequenceJob = viewModelScope.launch {
                startSequence()
            }
        }
    }

    private suspend fun startSequence() {
        resetState()

        for (i in 0 until 4) {
            delay(1000)
            _state.update { it.copy(activeIndex = i) }
        }

        val randomDelay = (500..3000).random()
        delay(randomDelay.toLong())

        _state.update { it.copy(activeIndex = -1) }
        startTimer()
    }

    private fun resetState() {
        _state.update {
            it.copy(
                reactionTime = null,
                isSequenceRunning = true
            )
        }
        startTime = null
    }

    private fun startTimer() {
        startTime = System.currentTimeMillis()
    }

    private fun stopTimer() {
        startTime?.let {
            val reactionTime = System.currentTimeMillis() - it
            _state.update {
                it.copy(
                    reactionTime = reactionTime / 1000.0,
                    isSequenceRunning = false
                )
            }
            startTime = null
        }
    }

    private fun stopSequence() {
        sequenceJob?.cancel()
        sequenceJob = null
        _state.update { it.copy(activeIndex = -1, isSequenceRunning = false) }
    }

    fun clearSequence() {
        stopSequence()
    }
}

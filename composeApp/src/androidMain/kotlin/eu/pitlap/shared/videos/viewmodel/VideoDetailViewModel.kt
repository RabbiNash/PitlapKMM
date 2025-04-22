package eu.pitlap.shared.videos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.videos.state.VideoDetailScreenEvent
import eu.pitlap.shared.videos.state.VideoDetailScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoDetailViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
): ViewModel() {
    private val _state = MutableStateFlow(VideoDetailScreenState())
    val state: StateFlow<VideoDetailScreenState> = _state.asStateFlow()


    fun onEvent(event: VideoDetailScreenEvent) {
        when (event) {
            is VideoDetailScreenEvent.LoadVideo -> loadData(videoId = event.videoId)
        }
    }

    private fun loadData(videoId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            viewModelScope.launch {
                try {
                    val video = pitlapService.getVideoById(videoId = videoId)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            video = video,
                        )
                    }
                } catch (e: Exception) {
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
            }
        }
    }
}

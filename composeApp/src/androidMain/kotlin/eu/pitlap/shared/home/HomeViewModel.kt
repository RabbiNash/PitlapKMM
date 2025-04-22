package eu.pitlap.shared.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.home.state.HomeScreenEvent
import eu.pitlap.shared.home.state.HomeScreenState
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
): ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.LoadData -> loadData()
            else -> Unit
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            viewModelScope.launch {
                try {
                    val feeds = pitlapService.getFeedArticles("https://www.autosport.com/rss/f1/news/")
                    val videos = pitlapService.getRankedVideos()

                    _state.update {
                        it.copy(
                            isLoading = false,
                            feeds = feeds,
                            videos = videos
                        )
                    }
                } catch (e: Exception) {
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
            }
        }
    }
}

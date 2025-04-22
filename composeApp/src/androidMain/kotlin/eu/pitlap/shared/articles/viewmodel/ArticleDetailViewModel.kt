package eu.pitlap.shared.articles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.articles.state.ArticleDetailScreenEvent
import eu.pitlap.shared.articles.state.ArticleDetailScreenState
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
): ViewModel() {
    private val _state = MutableStateFlow(ArticleDetailScreenState())
    val state: StateFlow<ArticleDetailScreenState> = _state.asStateFlow()


    fun onEvent(event: ArticleDetailScreenEvent) {
        when (event) {
            is ArticleDetailScreenEvent.LoadArticle -> loadData(articleId = event.id)
        }
    }

    private fun loadData(articleId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            viewModelScope.launch {
                try {
                    val article = pitlapService.getArticleFeedById(articleId)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            article = article,
                        )
                    }
                } catch (e: Exception) {
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
            }
        }
    }
}

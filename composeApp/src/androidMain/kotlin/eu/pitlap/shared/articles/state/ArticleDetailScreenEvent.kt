package eu.pitlap.shared.articles.state

sealed class ArticleDetailScreenEvent {
    data class LoadArticle(val id: String) : ArticleDetailScreenEvent()
}

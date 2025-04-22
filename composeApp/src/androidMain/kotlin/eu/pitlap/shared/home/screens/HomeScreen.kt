package eu.pitlap.shared.home.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.articles.screens.ArticleFeedItem
import eu.pitlap.shared.home.HomeViewModel
import eu.pitlap.shared.home.state.HomeScreenEvent
import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.ui.shared.HeaderText
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel
import eu.pitlap.shared.videos.screens.VideoItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onEvent: (HomeScreenEvent) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeScreenEvent.LoadData)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            LinearProgressIndicator(color = Color.Red)
        }
    } else if (state.error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
        }
    } else {
        ContentGrid(items = state.feeds, videos = state.videos, onEvent = onEvent)
    }
}

@Composable
fun ContentGrid(
    items: List<RSSFeedItem>,
    videos: List<YoutubeVideoModel>,
    onEvent: (HomeScreenEvent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 0.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Column {
                HeaderText(text = "Latest News")
                ArticleList(items, onEvent)
            }
        }

        item(span = { GridItemSpan(2) }) {
            HeaderText(text = "Latest Videos")
        }

        items(videos) {
            Box(
                modifier = Modifier.clickable { onEvent(HomeScreenEvent.LoadVideo(it)) }
            ) {
                VideoItem(it)
            }
        }
    }
}

@Composable
fun ArticleList(items: List<RSSFeedItem>, onEvent: (HomeScreenEvent) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) {
            Box(
                modifier = Modifier.clickable { onEvent(HomeScreenEvent.LoadArticle(it)) }
            ) {
                ArticleFeedItem(it)
            }
        }
    }
}

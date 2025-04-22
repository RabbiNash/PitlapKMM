package eu.pitlap.shared.articles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.pitlap.shared.articles.state.ArticleDetailScreenEvent
import eu.pitlap.shared.articles.viewmodel.ArticleDetailViewModel
import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.ui.pitlapTypography
import eu.pitlap.shared.utils.DateUtils

@Composable
fun ArticleDetail(
    articleId: String,
    viewModel: ArticleDetailViewModel = ArticleDetailViewModel(),
    onOpenLink: (String) -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ArticleDetailScreenEvent.LoadArticle(articleId))
    }

    state.article?.let { feed ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            HeaderImage(
                feed = feed,
                channelTitle = feed.channelTitle,
                onOpenLink = onOpenLink,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 240.dp, max = 400.dp)
            )

            ArticleMeta(feed = feed)

            Text(
                text = feed.description,
                style = pitlapTypography.bodyLarge,
                modifier = Modifier
                    .padding(16.dp)
            )

            ReadMoreLink(
                articleUrl = feed.link,
                onOpenLink = onOpenLink
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun HeaderImage(
    feed: RSSFeedItem,
    channelTitle: String,
    onOpenLink: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    ) {

        val imageUrl = remember(feed) {
            feed.imageUrl
        }

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f), Color.Black.copy(alpha = 0.5f)),
                        startY = 0f,
                        endY = 600f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = feed.title,
                style = pitlapTypography.displayMedium,
                color = Color.White,
                maxLines = 2
            )
            Text(
                text = feed.pubDate,
                style = pitlapTypography.labelMedium,
                color = Color.White
            )
            TextButton(
                onClick = { onOpenLink(feed.link) }
            ) {
                Icon(Icons.Default.Link, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = channelTitle,
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
                )
            }
        }
    }
}

@Composable
fun ArticleMeta(feed: RSSFeedItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = feed.description,
            style = pitlapTypography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ReadMoreLink(articleUrl: String, onOpenLink: (String) -> Unit) {
    TextButton(
        onClick = { onOpenLink(articleUrl) },
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Read more...",
            style = pitlapTypography.labelMedium
        )
    }
}

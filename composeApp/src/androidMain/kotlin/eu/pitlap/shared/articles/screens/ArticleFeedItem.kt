package eu.pitlap.shared.articles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.ui.pitlapTypography
import eu.pitlap.shared.utils.DateUtils

@Composable
fun ArticleFeedItem(feed: RSSFeedItem) {
    val width = LocalConfiguration.current.screenWidthDp.dp - 48.dp

    Box(
        modifier = Modifier
            .width(width)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
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
                .clip(RoundedCornerShape(16.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f), Color.Black.copy(alpha = 0.5f)),
                        startY = 0f,
                        endY = 600f
                    )
                )
                .clip(RoundedCornerShape(8.dp))
        )

        Column (
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = feed.title,
                color = Color.White,
                style = pitlapTypography.displaySmall,
                maxLines = 4
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    imageVector = Icons.Default.Link,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    text = feed.channelTitle,
                    color = Color.White,
                    style = pitlapTypography.bodyMedium,
                    maxLines = 1
                )
            }

            Text(
                text = feed.pubDate.let { DateUtils.getHumanisedShortDate(it) } ?: "",
                color = Color.White,
                style = pitlapTypography.labelSmall,
            )
        }
    }
}

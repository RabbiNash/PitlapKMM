package eu.pitlap.shared.videos.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.pitlap.shared.ui.pitlapTypography
import eu.pitlap.shared.videos.YouTubePlayer
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel
import eu.pitlap.shared.videos.state.VideoDetailScreenEvent
import eu.pitlap.shared.videos.viewmodel.VideoDetailViewModel

@Composable
fun VideoDetail(
    viewModel: VideoDetailViewModel = viewModel(),
    videoId: String
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onEvent(VideoDetailScreenEvent.LoadVideo(videoId))
    }

    state.video?.let { video ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            YouTubePlayer(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(8.dp)),
                videoId = video.videoId
            )

            Text(
                text = video.title,
                fontSize = 16.sp,
                style = pitlapTypography.displayMedium,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = video.description,
                style = pitlapTypography.bodyLarge,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

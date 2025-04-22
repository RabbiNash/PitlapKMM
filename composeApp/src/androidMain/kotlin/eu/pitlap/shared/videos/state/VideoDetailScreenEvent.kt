package eu.pitlap.shared.videos.state

sealed class VideoDetailScreenEvent {
    data class LoadVideo(val videoId: String) : VideoDetailScreenEvent()
}

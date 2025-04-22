package eu.pitlap.shared.cache.dao.videos

import eu.pitlap.shared.cache.PitlapDBQueries
import eu.pitlap.shared.cache.YoutubeMeta
import eu.pitlap.shared.cache.YoutubeMetaQueries
import eu.pitlap.shared.cache.mapper.VideoMapper
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

internal class VideoDAOImpl(
    private val videoQueries: PitlapDBQueries,
    private val metaQueries: YoutubeMetaQueries
): VideoDAO {
    override fun getAllVideos(): List<YoutubeVideoModel> =
        videoQueries.selectAllVideos(VideoMapper::mapToVideoModel).executeAsList()

    override fun getVideoById(id: String): YoutubeVideoModel =
        videoQueries.selectVideoById(id, VideoMapper::mapToVideoModel).executeAsOne()

    override fun getVideosByChannelName(channelName: String): List<YoutubeVideoModel> =
        videoQueries.selectVideoByChannelName(channelName, VideoMapper::mapToVideoModel).executeAsList()

    override fun clearAndCreateVideos(channelName: String, videos: List<YoutubeVideoModel>) {
        videos.forEach { insertOrReplaceVideo(channelName, it) }
    }

    override fun getVideoMeta(): YoutubeMeta? {
        return metaQueries.getMeta().executeAsOneOrNull()
    }

    override fun insertMeta(timestamp: String) {
        metaQueries.clearMeta()
        metaQueries.insertMeta(timestamp)
    }

    private fun insertOrReplaceVideo(channelName: String, video: YoutubeVideoModel) {
        videoQueries.insertOrReplaceVideo(
            video_id = video.videoId,
            thumbnail_url = video.thumbnailUrl,
            title = video.title,
            description = video.description,
            channel_name = channelName,
            created_at = video.publishedAt,
            updated_at = video.publishedAt,
        )
    }
}

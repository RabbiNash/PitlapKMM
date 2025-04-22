package eu.pitlap.shared.cache.dao.videos

import eu.pitlap.shared.cache.YoutubeMeta
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel

interface VideoDAO {
    fun getAllVideos(): List<YoutubeVideoModel>
    fun getVideoById(id: String): YoutubeVideoModel
    fun getVideosByChannelName(channelName: String): List<YoutubeVideoModel>
    fun clearAndCreateVideos(channelName: String, videos: List<YoutubeVideoModel>)
    fun getVideoMeta(): YoutubeMeta?
    fun insertMeta(timestamp: String)
}

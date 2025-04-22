package eu.pitlap.shared.videos.data.repository

import eu.pitlap.shared.cache.Database
import eu.pitlap.shared.cache.DatabaseProvider
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.videos.data.source.YoutubeVideosDataSource
import eu.pitlap.shared.videos.data.source.YoutubeVideosDataSourceImpl
import eu.pitlap.shared.videos.domain.mapper.YoutubeVideoMapper
import eu.pitlap.shared.videos.domain.model.Channels
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel
import eu.pitlap.shared.videos.domain.repository.YoutubeVideosRepository
import kotlin.random.Random

internal class YoutubeVideosRepositoryImpl(
    private val dataSource: YoutubeVideosDataSource = YoutubeVideosDataSourceImpl(),
    private val database: Database = DatabaseProvider.get(),
    private val mapper: YoutubeVideoMapper = YoutubeVideoMapper()
): YoutubeVideosRepository {
    override suspend fun getVideos(channelName: String): List<YoutubeVideoModel> {
        database.getYTMeta()
        val cachedEvents = database.getVideosByChannelName(channelName)
        return cachedEvents.ifEmpty {
            when (val result = dataSource.getVideos(channelName)) {
                is Result.Success -> {
                    val data = mapper.mapToYoutubeVideoModel(result.data)
                    database.clearAndCreateVideos(channelName = channelName, data)
                    data
                }
                is Result.Error -> throw result.error.toThrowable()
            }
        }
    }

    override suspend fun getRankedVideos(): List<YoutubeVideoModel> {
        val cachedVideos = database.getAllVideos()
        return cachedVideos.ifEmpty {
            Channels.entries.flatMap { channel ->
                val videos = getVideos(channel.channelName)
                val count = Random.nextInt(3, 8)
                if (videos.size <= 3) {
                    videos
                } else {
                    videos.shuffled().take(count.coerceAtMost(videos.size))
                }
            }
        }
    }

    override suspend fun getVideoById(videoId: String): YoutubeVideoModel? {
        return database.getVideoById(videoId)
    }
}

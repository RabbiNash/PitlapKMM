package eu.pitlap.shared.videos.data.repository

import eu.pitlap.shared.cache.dao.videos.VideoDAO
import eu.pitlap.shared.cache.factory.DatabaseProvider
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.core.domain.toThrowable
import eu.pitlap.shared.videos.data.source.YoutubeVideosDataSource
import eu.pitlap.shared.videos.data.source.YoutubeVideosDataSourceImpl
import eu.pitlap.shared.videos.domain.mapper.YoutubeVideoMapper
import eu.pitlap.shared.videos.domain.model.Channels
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel
import eu.pitlap.shared.videos.domain.repository.YoutubeVideosRepository
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal class YoutubeVideosRepositoryImpl(
    private val dataSource: YoutubeVideosDataSource = YoutubeVideosDataSourceImpl(),
    private val dao: VideoDAO = DatabaseProvider.getVideoDAO(),
    private val mapper: YoutubeVideoMapper = YoutubeVideoMapper()
): YoutubeVideosRepository {
    private val ttl = Duration.parse("PT1H")

    override suspend fun getVideos(channelName: String, forceRefresh: Boolean): List<YoutubeVideoModel> {
        val cachedEvents = dao.getVideosByChannelName(channelName)
        return if (forceRefresh) {
            when (val result = dataSource.getVideos(channelName)) {
                is Result.Success -> {
                    val data = mapper.mapToYoutubeVideoModel(result.data)
                    dao.clearAndCreateVideos(channelName = channelName, data)
                    data
                }
                is Result.Error -> {
                    return cachedEvents.ifEmpty {
                        throw result.error.toThrowable()
                    }
                }
            }
        } else {
            cachedEvents
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun getRankedVideos(forceRefresh: Boolean): List<YoutubeVideoModel> {
        val cachedVideos = dao.getAllVideos()
        val meta = dao.getVideoMeta()
        val videos = if (meta != null && Clock.System.now() - Instant.parse(meta.last_fetched) < ttl) {
            cachedVideos
        } else {
            emptyList()
        }

        return if (forceRefresh) {
            getVideosForRanking()
        } else {
            videos
        }
    }

    override suspend fun getVideoById(videoId: String): YoutubeVideoModel? {
        return dao.getVideoById(videoId)
    }

    @OptIn(ExperimentalTime::class)
    private suspend fun getVideosForRanking(): List<YoutubeVideoModel> {
        dao.insertMeta(Clock.System.now().toString())
        return Channels.entries.flatMap { channel ->
            val videos = getRemoteVideos(channel.channelName)
            val count = Random.nextInt(3, 8)
            if (videos.size <= 3) {
                videos
            } else {
                videos.shuffled().take(count.coerceAtMost(videos.size))
            }
        }
    }

    private suspend fun getRemoteVideos(channelName: String): List<YoutubeVideoModel> {
        return when (val result = dataSource.getVideos(channelName)) {
            is Result.Success -> {
                val data = mapper.mapToYoutubeVideoModel(result.data)
                dao.clearAndCreateVideos(channelName = channelName, data)
                data
            }
            is Result.Error -> {
                throw result.error.toThrowable()
            }
        }
    }
}

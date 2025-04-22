package eu.pitlap.shared.modules

import eu.pitlap.shared.race.data.repository.SummaryRepositoryImpl
import eu.pitlap.shared.race.data.repository.TrackEventsRepositoryImpl
import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel
import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TopSpeedModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel
import eu.pitlap.shared.race.domain.repository.SummaryRepository
import eu.pitlap.shared.race.domain.repository.TrackEventsRepository
import eu.pitlap.shared.rss.data.repository.RSSFeedRepositoryImpl
import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.rss.domain.repository.RSSFeedRepository
import eu.pitlap.shared.schedule.data.repository.ScheduleRepositoryImpl
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.domain.repository.ScheduleRepository
import eu.pitlap.shared.standings.data.repository.StandingsRepositoryImpl
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import eu.pitlap.shared.standings.domain.repository.StandingsRepository
import eu.pitlap.shared.videos.data.repository.YoutubeVideosRepositoryImpl
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel
import eu.pitlap.shared.videos.domain.repository.YoutubeVideosRepository
import eu.pitlap.shared.weather.data.repository.WeatherRepositoryImpl
import eu.pitlap.shared.weather.domain.model.WeatherModel
import eu.pitlap.shared.weather.domain.repository.WeatherRepository

internal class PitlapServiceImpl(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl(),
    private val scheduleRepository: ScheduleRepository = ScheduleRepositoryImpl(),
    private val standingsRepository: StandingsRepository = StandingsRepositoryImpl(),
    private val trackEventsRepository: TrackEventsRepository = TrackEventsRepositoryImpl(),
    private val youtubeVideosRepository: YoutubeVideosRepository = YoutubeVideosRepositoryImpl(),
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(),
    private val rssFeedRepository: RSSFeedRepository = RSSFeedRepositoryImpl()
): PitlapService {
    override suspend fun getDriverStandings(): List<DriverStandingModel> {
        return standingsRepository.getDriverStandings()
    }

    override suspend fun getConstructorStandings(): List<ConstructorStandingModel> {
        return standingsRepository.getConstructorStandings()
    }

    override suspend fun getPracticeLaps(
        year: Int,
        round: Int,
        sessionName: String
    ): List<GroupedLapModel> {
        return trackEventsRepository.getPracticeLaps(year, round, sessionName)
    }

    override suspend fun getQualifyingResults(year: Int, round: Int): List<QualifyingResultModel> {
        return trackEventsRepository.getQualifyingResults(year, round)
    }

    override suspend fun getRaceResults(year: Int, round: Int): List<RaceResultModel> {
        return trackEventsRepository.getRaceResults(year, round)
    }

    override suspend fun getSchedule(year: Int): List<EventScheduleModel> {
        return scheduleRepository.getSchedule(year)
    }

    override suspend fun getEvent(year: Int, round: Int): EventScheduleModel? {
        return scheduleRepository.getEvent(year, round)
    }

    override suspend fun getRaceSummary(year: Int, round: Int): RaceSummaryModel {
        return summaryRepository.getRaceSummary(year, round)
    }

    override suspend fun getTrackSummary(trackName: String): TrackSummaryModel {
        return summaryRepository.getTrackSummary(trackName)
    }

    override suspend fun getYTVideos(channelName: String): List<YoutubeVideoModel> {
        return youtubeVideosRepository.getVideos(channelName)
    }

    override suspend fun getRankedVideos(): List<YoutubeVideoModel> {
        return youtubeVideosRepository.getRankedVideos()
    }

    override suspend fun getVideoById(videoId: String): YoutubeVideoModel? {
        return youtubeVideosRepository.getVideoById(videoId = videoId)
    }

    override suspend fun getWeather(year: Int, round: Int): WeatherModel {
       return weatherRepository.getWeatherSummary(year, round)
    }

    override suspend fun getFeedArticles(feedUrl: String): List<RSSFeedItem> {
        return rssFeedRepository.getRSSFeed(feedUrl)
    }

    override suspend fun getArticleFeedById(id: String): RSSFeedItem? {
        return rssFeedRepository.getArticleFeedById(id)
    }

    override suspend fun getSessionTopSpeeds(
        year: Int,
        round: Int,
        sessionName: String
    ): List<TopSpeedModel> {
        return trackEventsRepository.getTopSpeeds(year, round, sessionName)
    }
}

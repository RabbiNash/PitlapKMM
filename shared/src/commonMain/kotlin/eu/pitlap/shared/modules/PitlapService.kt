package eu.pitlap.shared.modules

import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel
import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TopSpeedModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel
import eu.pitlap.shared.rss.domain.RSSFeedItem
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import eu.pitlap.shared.videos.domain.model.YoutubeVideoModel
import eu.pitlap.shared.weather.domain.model.WeatherModel

interface PitlapService {
    @Throws(Throwable::class)
    suspend fun getDriverStandings(forceRefresh: Boolean = false): List<DriverStandingModel>
    @Throws(Throwable::class)
    suspend fun getConstructorStandings(forceRefresh: Boolean = false): List<ConstructorStandingModel>
    @Throws(Throwable::class)
    suspend fun getPracticeLaps(year: Int, round: Int, sessionName: String): List<GroupedLapModel>
    @Throws(Throwable::class)
    suspend fun getQualifyingResults(year: Int, round: Int): List<QualifyingResultModel>
    @Throws(Throwable::class)
    suspend fun getRaceResults(year: Int, round: Int): List<RaceResultModel>
    @Throws(Throwable::class)
    suspend fun getSchedule(year: Int, forceRefresh: Boolean = false): List<EventScheduleModel>
    @Throws(Throwable::class)
    suspend fun getEvent(year: Int, round: Int): EventScheduleModel?
    @Throws(Throwable::class)
    suspend fun getNextScheduledEvent(): EventScheduleModel?
    @Throws(Throwable::class)
    suspend fun getRaceSummary(year: Int, round: Int): RaceSummaryModel
    @Throws(Throwable::class)
    suspend fun getTrackSummary(trackName: String): TrackSummaryModel
    @Throws(Throwable::class)
    suspend fun getYTVideos(channelName: String, forceRefresh: Boolean = false): List<YoutubeVideoModel>
    @Throws(Throwable::class)
    suspend fun getRankedVideos(forceRefresh: Boolean = false): List<YoutubeVideoModel>
    @Throws(Throwable::class)
    suspend fun getVideoById(videoId: String): YoutubeVideoModel?
    @Throws(Throwable::class)
    suspend fun getWeather(year: Int, round: Int): WeatherModel
    @Throws(Throwable::class)
    suspend fun getFeedArticles(feedUrl: String, forceRefresh: Boolean = false): List<RSSFeedItem>
    @Throws(Throwable::class)
    suspend fun getArticleFeedById(id: String): RSSFeedItem?
    @Throws(Throwable::class)
    suspend fun getSessionTopSpeeds(year: Int, round: Int, sessionName: String): List<TopSpeedModel>
}

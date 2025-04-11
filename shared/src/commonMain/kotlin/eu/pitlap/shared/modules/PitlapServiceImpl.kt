package eu.pitlap.shared.modules

import eu.pitlap.shared.race.data.repository.SummaryRepositoryImpl
import eu.pitlap.shared.race.data.repository.TrackEventsRepositoryImpl
import eu.pitlap.shared.race.domain.model.GroupedLapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel
import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel
import eu.pitlap.shared.race.domain.repository.SummaryRepository
import eu.pitlap.shared.race.domain.repository.TrackEventsRepository
import eu.pitlap.shared.schedule.data.repository.ScheduleRepositoryImpl
import eu.pitlap.shared.schedule.domain.model.EventScheduleModel
import eu.pitlap.shared.schedule.domain.repository.ScheduleRepository
import eu.pitlap.shared.standings.data.repository.StandingsRepositoryImpl
import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import eu.pitlap.shared.standings.domain.repository.StandingsRepository

internal class PitlapServiceImpl(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl(),
    private val scheduleRepository: ScheduleRepository = ScheduleRepositoryImpl(),
    private val standingsRepository: StandingsRepository = StandingsRepositoryImpl(),
    private val trackEventsRepository: TrackEventsRepository = TrackEventsRepositoryImpl()
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

    override suspend fun getRaceSummary(year: Int, round: Int): RaceSummaryModel {
        return summaryRepository.getRaceSummary(year, round)
    }

    override suspend fun getTrackSummary(trackName: String): TrackSummaryModel {
        return summaryRepository.getTrackSummary(trackName)
    }
}

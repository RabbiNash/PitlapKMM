package eu.pitlap.shared.schedule.state.analysis

import eu.pitlap.shared.schedule.models.SessionModel

sealed class SessionDetailScreenEvent {
    data class LoadQualifying(val sessionModel: SessionModel) : SessionDetailScreenEvent()
    data class LoadRaceResult(val sessionModel: SessionModel) : SessionDetailScreenEvent()
    data class LoadPracticeLaps(val sessionModel: SessionModel) : SessionDetailScreenEvent()
    data class LoadTopSpeeds(val sessionModel: SessionModel) : SessionDetailScreenEvent()
}

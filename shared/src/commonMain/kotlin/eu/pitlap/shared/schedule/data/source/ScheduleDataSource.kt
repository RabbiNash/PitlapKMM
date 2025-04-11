package eu.pitlap.shared.schedule.data.source

import eu.pitlap.shared.core.domain.ApiError
import eu.pitlap.shared.core.domain.Result
import eu.pitlap.shared.schedule.data.dto.EventScheduleDto

internal interface ScheduleDataSource {
    suspend fun getEventSchedule(year: Int): Result<List<EventScheduleDto>, ApiError.Remote>
}


package eu.pitlap.shared.race.domain.mapper

import eu.pitlap.shared.race.data.dto.RaceSummaryDto
import eu.pitlap.shared.race.data.dto.TrackSummaryDto
import eu.pitlap.shared.race.domain.model.RaceSummaryModel
import eu.pitlap.shared.race.domain.model.TrackSummaryModel

internal fun RaceSummaryDto.toRaceSummaryModel(): RaceSummaryModel {
    return RaceSummaryModel(summary = summary)
}

internal fun TrackSummaryDto.toTrackSummaryModel(): TrackSummaryModel {
    return TrackSummaryModel(
        fact = fact,
        track = track,
        circuitLengthKm = circuitLengthKm,
        firstRace = firstRace
    )
}

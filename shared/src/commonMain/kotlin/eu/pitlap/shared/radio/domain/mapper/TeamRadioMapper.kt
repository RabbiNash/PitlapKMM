package eu.pitlap.shared.radio.domain.mapper

import eu.pitlap.shared.radio.data.dto.TeamRadioDto
import eu.pitlap.shared.radio.domain.model.TeamRadioModel

internal fun TeamRadioDto.toDomainModel() : TeamRadioModel {
    return TeamRadioModel(
        date = this.date,
        driverNumber = this.driverNumber,
        meetingKey = this.meetingKey,
        recordingUrl = this.recordingUrl,
        sessionKey = this.sessionKey
    )
}
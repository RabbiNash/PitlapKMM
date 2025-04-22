package eu.pitlap.shared.race.domain.mapper

import eu.pitlap.shared.race.data.dto.TopSpeedsDto
import eu.pitlap.shared.race.domain.model.TopSpeedModel

internal fun TopSpeedsDto.toTopSpeedsModel(): List<TopSpeedModel> {
    return this.speeds.map {
        TopSpeedModel(
            driver = it.driver,
            speed = it.speed
        )
    }.sortedByDescending { it.speed }
}

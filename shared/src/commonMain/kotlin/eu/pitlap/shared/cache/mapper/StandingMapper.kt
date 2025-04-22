package eu.pitlap.shared.cache.mapper

import eu.pitlap.shared.standings.domain.model.ConstructorStandingModel
import eu.pitlap.shared.standings.domain.model.DriverStandingModel

object StandingMapper {
    fun mapToDriverStandingModel(
        position: Long,
        positionText: String,
        points: Long,
        wins: Long,
        driverId: String,
        driverNumber: Long,
        givenName: String,
        familyName: String,
        constructorName: String,
    ): DriverStandingModel = DriverStandingModel(
        position = position.toInt(),
        positionText = positionText,
        points = points.toInt(),
        wins = wins.toInt(),
        driverId = driverId,
        driverNumber = driverNumber.toInt(),
        givenName = givenName,
        familyName = familyName,
        constructorName = constructorName,
    )

    fun mapToConstructorStandingModel(
        position: Long,
        positionText: String,
        points: Long,
        wins: Long,
        constructorId: String,
        constructorName: String,
    ): ConstructorStandingModel = ConstructorStandingModel(
        position = position.toInt(),
        positionText = positionText,
        points = points.toInt(),
        wins = wins.toInt(),
        constructorId = constructorId,
        constructorName = constructorName,
    )
}

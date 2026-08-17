package eu.pitlap.shared.race.domain.mapper

import eu.pitlap.shared.race.data.dto.LapDto
import eu.pitlap.shared.core.data.models.ergast.tables.RaceTable
import eu.pitlap.shared.race.domain.model.LapModel
import eu.pitlap.shared.race.domain.model.QualifyingResultModel
import eu.pitlap.shared.race.domain.model.RaceResultModel

internal fun LapDto.toLapModel(): LapModel {
    return LapModel(
        driver = driver,
        headshotUrl = headshotUrl,
        compound = compound,
        lapTime = lapTime,
        lapNumber = lapNumber,
        fullName = fullName
    )
}

internal fun RaceTable.toQualifyingResultsModel(): List<QualifyingResultModel> {
    return races.singleOrNull()?.qualifyingResults.orEmpty().map {
        QualifyingResultModel(
            position = it.position.toIntOrNull() ?: 0,
            q1 = it.q1,
            q2 = it.q2,
            q3 = it.q3,
            teamName = it.constructor.name,
            headshotUrl = "",
            fullName = "${it.driver.givenName} ${it.driver.familyName}"
        )
    }
}

internal fun RaceTable.toRaceResultsModel(): List<RaceResultModel> {
    return races.singleOrNull()?.results.orEmpty().map {
        RaceResultModel(
            position = it.position.toIntOrNull() ?: 0,
            points = it.points.toIntOrNull() ?: 0,
            teamName = it.constructor.name,
            fullName = "${it.driver.givenName} ${it.driver.familyName}",
            headshotURL = "",
            gridPosition = it.grid.toIntOrNull() ?: 0,
            classifiedPosition = it.positionText,
        )
    }
}

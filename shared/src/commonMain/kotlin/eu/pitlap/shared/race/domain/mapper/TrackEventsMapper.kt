package eu.pitlap.shared.race.domain.mapper

import eu.pitlap.shared.race.data.dto.LapDto
import eu.pitlap.shared.race.data.dto.QualifyingResultsDto
import eu.pitlap.shared.race.data.dto.RaceResultsDto
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

internal fun QualifyingResultsDto.toResultsModel() : List<QualifyingResultModel> {
    return this.results.map {
        QualifyingResultModel(
            position = it.position,
            q1 = it.q1,
            q2 = it.q2,
            q3 = it.q3,
            teamName = it.teamName,
            headshotUrl = it.headshotUrl,
            fullName = it.fullName
        )
    }
}

internal fun RaceResultsDto.toResultsModel(): List<RaceResultModel> {
    return results.map {
        RaceResultModel(
            position = it.position,
            points = it.points,
            teamName = it.teamName,
            fullName = it.fullName,
            headshotURL = it.headshotURL,
            gridPosition = it.gridPosition,
            classifiedPosition = it.classifiedPosition,
        )
    }
}

package eu.pitlap.shared.core.data.resolver

import eu.pitlap.shared.core.data.models.ergast.tables.ErgastTable
import eu.pitlap.shared.core.data.models.ergast.tables.RaceTable
import eu.pitlap.shared.core.data.models.ergast.tables.StandingsTable
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object ErgastTableSerializer :
    JsonContentPolymorphicSerializer<ErgastTable>(ErgastTable::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ErgastTable> {
        val obj = element.jsonObject

        return when {
            "StandingsLists" in obj -> StandingsTable.serializer()
            "Races" in obj -> RaceTable.serializer()
            else -> error("Unknown Ergast table type: ${obj.keys}")
        }
    }
}

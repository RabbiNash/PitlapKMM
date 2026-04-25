package eu.pitlap.shared.core.data.models.ergast

import eu.pitlap.shared.core.data.models.ergast.tables.ErgastTable
import eu.pitlap.shared.core.data.resolver.MRDataSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MRDataSerializer::class)
data class MRData(
    val xmlns: String,
    val series: String,
    val url: String,
    val limit: String,
    val offset: String,
    val total: String,
    val table: ErgastTable
)

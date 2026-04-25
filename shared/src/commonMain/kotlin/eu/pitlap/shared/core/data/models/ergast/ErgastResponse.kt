package eu.pitlap.shared.core.data.models.ergast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErgastResponse(
    @SerialName("MRData")
    val mrData: MRData
)

package eu.pitlap.shared.videos.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class YoutubeVideoDto(
    val thumbnails: Thumbnails,
    @SerialName("resourceId")
    val resourceID: MediaResource,
    @SerialName("_id")
    val id: String,
    @SerialName("__v")
    val v: Int,
    @SerialName("channelId")
    val channelID: String,
    val channelTitle: String,
    val description: String,
    @SerialName("playlistId")
    val playlistID: String,
    val position: Int,
    val publishedAt: String,
    val title: String,
    val updatedAt: String,
    @SerialName("videoOwnerChannelId")
    val videoOwnerChannelID: String,
    val videoOwnerChannelTitle: String
)

@Serializable
internal data class MediaResource(
    val kind: String,
    @SerialName("videoId")
    val videoID: String
)

@Serializable
internal data class Thumbnails(
    @SerialName("default")
    val thumbnailsDefault: ThumbnailDetails,
    val medium: ThumbnailDetails,
    val high: ThumbnailDetails,
    val standard: ThumbnailDetails,
    val maxres: ThumbnailDetails? = null
)

@Serializable
internal data class ThumbnailDetails(
    val url: String,
    val width: Int,
    val height: Int
)

package com.example.articbrowser.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtDto(
    @Json(name="id") val id: Int,
    @Json(name="title") val title: String,
    @Json(name="artist_title") val artistTitle: String? = null,
    @Json(name="artist_display") val artistDisplay: String? = null,
    @Json(name="image_id") val imageId: String? = null,
    @Json(name="short_description") val description: String? = null,
    @Json(name="publication_history") val publicationHistory: String? = null,
    @Json(name="exhibition_history") val exhibitionHistory: String? = null,
    @Json(name="date_display") val dateDisplay: String? = null,
    @Json(name="place_of_origin") val placeOfOrigin: String? = null
)

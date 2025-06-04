package com.example.articbrowser.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ArtDetailedDto(
    @Json(name="data")val data: ArtDto
)
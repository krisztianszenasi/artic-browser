package com.example.articbrowser.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtPaginatedDto(
    @Json(name="pagination")val pagination: PaginationInfo,
    @Json(name="data")val data: List<ArtDto>
)

@JsonClass(generateAdapter = true)
data class PaginationInfo(
    @Json(name="total")val total: Int,
    @Json(name="limit")val limit: Int,
    @Json(name="offset")val offset: Int,
    @Json(name="total_pages")val totalPages: Int,
    @Json(name="current_page")val currentPage: Int,
    @Json(name="prev_url")val prevUrl: String?,
    @Json(name="next_url")val nextUrl: String?
)

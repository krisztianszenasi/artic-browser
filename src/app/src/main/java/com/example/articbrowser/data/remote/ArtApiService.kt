package com.example.articbrowser.data.remote

import com.example.articbrowser.data.remote.dto.ArtDetailedDto
import com.example.articbrowser.data.remote.dto.ArtPaginatedDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApiService {
    @GET("artworks")
    suspend fun fetchArts(
        @Query("page") page: Int,
        @Query("limit") size: Int,
        @Query("fields") fields: String = "id,title,artist_title,image_id",
    ): ArtPaginatedDto

    @GET("artworks/search")
    suspend fun searchArts(
        @Query("params") params: String,
        @Query("page") page: Int,
        @Query("limit") size: Int,
        @Query("fields") fields: String = "id,title,artist_title,image_id",
    ): ArtPaginatedDto

    @GET("artworks/{id}")
    suspend fun fetchArtById(
        @Path("id") id: Int,
        @Query("fields") fields: String = "id,title,artist_title,artist_display,image_id,short_description,date_display,place_of_origin,publication_history,exhibition_history"
    ): ArtDetailedDto

    companion object {
        const val BASE_URL = "https://api.artic.edu/api/v1/"
    }
}
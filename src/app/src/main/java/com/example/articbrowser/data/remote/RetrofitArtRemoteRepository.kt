package com.example.articbrowser.data.remote

import retrofit2.HttpException
import com.example.articbrowser.data.ResponseWrapper
import com.example.articbrowser.data.remote.dto.toArt
import com.example.articbrowser.domain.Art
import okio.IOException
import javax.inject.Inject
import org.json.JSONObject

class RetrofitArtRemoteRepository @Inject constructor(
    private val artApiService: ArtApiService
): ArtRemoteRepository {
    override suspend fun fetchArts(page: Int, size: Int): ResponseWrapper<List<Art>> {
        try {
            val response = artApiService.fetchArts(page, size)
            return ResponseWrapper(
                data = response.data.map { it.toArt() }
            )
        } catch(e: IOException) {
            return ResponseWrapper(exception = e)
        } catch (e: HttpException) {
            return ResponseWrapper(exception = e)
        }
    }

    override suspend fun searchArts(query: String, page: Int, size: Int): ResponseWrapper<List<Art>> {
        try {
            val response = artApiService.searchArts(
                params = constructElasticSearchQuery(query),
                page = page,
                size = size
            )
            return ResponseWrapper(
                data = response.data.map { it.toArt() }
            )
        } catch(e: IOException) {
            return ResponseWrapper(exception = e)
        } catch (e: HttpException) {
            return ResponseWrapper(exception = e)
        }
    }
    override suspend fun fetchArtDetail(id: Int): ResponseWrapper<Art> {
        try {
            val response = artApiService.fetchArtById(id)
            return ResponseWrapper(
                data = response.data.toArt()
            )
        } catch (e: IOException) {
            return ResponseWrapper(exception = e)
        } catch (e: HttpException) {
            return ResponseWrapper(exception = e)
        }
    }

    private fun constructElasticSearchQuery(query: String): String {
        val json = JSONObject(
            mapOf(
                "query" to mapOf(
                    "multi_match" to mapOf(
                        "query" to query,
                        "fields" to listOf(
                            "title", "artist_title"
                        )
                    )
                )
            )
        )

        return json.toString()
    }
}
package com.example.articbrowser.data.remote

import com.example.articbrowser.data.ResponseWrapper
import com.example.articbrowser.domain.Art


interface ArtRemoteRepository {
    suspend fun fetchArts(page: Int, size: Int): ResponseWrapper<List<Art>>
    suspend fun searchArts(query: String, page: Int, size: Int): ResponseWrapper<List<Art>>
    suspend fun fetchArtDetail(id: Int): ResponseWrapper<Art>
}
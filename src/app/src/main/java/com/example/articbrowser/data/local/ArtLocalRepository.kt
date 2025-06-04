package com.example.articbrowser.data.local

import androidx.paging.PagingData
import com.example.articbrowser.domain.Art
import kotlinx.coroutines.flow.Flow


interface ArtLocalRepository {
    fun getPagedArtFlow(query: String?): Flow<PagingData<Art>>
}
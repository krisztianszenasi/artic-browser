package com.example.articbrowser.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.articbrowser.data.remote.ArtRemoteMediator
import com.example.articbrowser.domain.Art
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PagingArtLocalRepository @Inject constructor(
    private val artDatabase: ArtDatabase,
    private val artRemoteMediatorFactory: ArtRemoteMediator.Factory
): ArtLocalRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedArtFlow(query: String?): Flow<PagingData<Art>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = artRemoteMediatorFactory.create(query),
            pagingSourceFactory = { artDatabase.dao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { it.toArt() }
        }
    }
}
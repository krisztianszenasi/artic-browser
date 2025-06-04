package com.example.articbrowser.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.articbrowser.data.ResponseWrapper
import com.example.articbrowser.data.local.ArtDatabase
import com.example.articbrowser.data.local.entity.ArtEntity
import com.example.articbrowser.data.local.toArtEntity
import com.example.articbrowser.domain.Art
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@OptIn(ExperimentalPagingApi::class)
class ArtRemoteMediator @AssistedInject constructor(
    @Assisted private val query: String? = null,
    private val artDb: ArtDatabase,
    private val artRemoteRepository: ArtRemoteRepository
): RemoteMediator<Int, ArtEntity>() {

    private var previousPage: Int? = null

    @AssistedFactory
    interface Factory {
        fun create(query: String?): ArtRemoteMediator
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArtEntity>
    ): MediatorResult {
        val loadKey = getLoadKey(loadType, state)
            ?: return MediatorResult.Success(endOfPaginationReached = true)


        val responseWrapper: ResponseWrapper<List<Art>> = fetchArts(loadKey!!, state)
        if (!responseWrapper.isSuccessful) {
            return MediatorResult.Error(responseWrapper.exception!!)
        }

        val arts = responseWrapper.data!!
        artDb.withTransaction {
            if(loadType == LoadType.REFRESH) {
                artDb.dao.clearAll()
            }
            val artEntities = arts.map{ it.toArtEntity() }
            artDb.dao.upsertAll(artEntities)
        }

        return MediatorResult.Success(
            endOfPaginationReached = arts.isEmpty()
        )

    }

    private fun getLoadKey(
        loadType: LoadType,
        state: PagingState<Int, ArtEntity>
    ): Int? {
            when(loadType) {
                LoadType.REFRESH -> {
                    previousPage = 1
                }
                LoadType.PREPEND -> {
                    previousPage = null
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    previousPage = if (lastItem == null) {
                        1
                    } else {
                        (previousPage ?: 0) + 1
                    }
                }
            }
        return previousPage
    }

    private suspend fun fetchArts(loadKey: Int, state: PagingState<Int, ArtEntity>): ResponseWrapper<List<Art>> {
        return if (!query.isNullOrBlank()) {
            artRemoteRepository.searchArts(query = query, page = loadKey, size = state.config.pageSize)
        } else {
            artRemoteRepository.fetchArts(page = loadKey, size = state.config.pageSize)
        }
    }
}
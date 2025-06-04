package com.example.articbrowser.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.articbrowser.data.local.entity.ArtEntity

@Dao
interface ArtDao {

    @Upsert
    suspend fun upsertAll(arts: List<ArtEntity>)

    @Query("SELECT * FROM arts")
    fun pagingSource(): PagingSource<Int, ArtEntity>

    @Query("DELETE FROM arts")
    suspend fun clearAll()
}
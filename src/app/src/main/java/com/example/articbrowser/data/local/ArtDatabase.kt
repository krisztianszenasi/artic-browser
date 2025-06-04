package com.example.articbrowser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.articbrowser.data.local.dao.ArtDao
import com.example.articbrowser.data.local.entity.ArtEntity

@Database(
    entities = [ArtEntity::class],
    version = 4,
    exportSchema = false
)
abstract class ArtDatabase: RoomDatabase() {

    abstract val dao: ArtDao
}
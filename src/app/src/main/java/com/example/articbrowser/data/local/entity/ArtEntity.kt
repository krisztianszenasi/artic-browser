package com.example.articbrowser.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class ArtEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val artistTitle: String? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val dateDisplay: String? = null,
    val artistDisplay: String? = null,
    val placeOfOrigin: String? = null
)
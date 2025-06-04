package com.example.articbrowser.domain

data class Art(
    val id: Int,
    val title: String,
    val artistTitle: String,
    val imageUrl: String? = null,
    val description: String? = null,
    val dateDisplay: String? = null,
    val artistDisplay: String? = null,
    val publicationHistory: String? = null,
    val exhibitionHistory: String? = null,
    val placeOfOrigin: String? = null
)

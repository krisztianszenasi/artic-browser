package com.example.articbrowser.data.local

import com.example.articbrowser.data.local.entity.ArtEntity
import com.example.articbrowser.domain.Art

fun ArtEntity.toArt(): Art {
    return Art(
        id = id,
        title = title,
        artistDisplay = artistDisplay,
        imageUrl =  imageUrl,
        description = description,
        dateDisplay = dateDisplay,
        artistTitle = artistTitle ?: "unknown",
        placeOfOrigin = placeOfOrigin
    )
}

fun Art.toArtEntity(): ArtEntity {
    return ArtEntity(
        id = id,
        title = title,
        artistDisplay = artistDisplay,
        imageUrl = imageUrl,
        description = description,
        dateDisplay = dateDisplay,
        artistTitle = artistTitle,
        placeOfOrigin = placeOfOrigin
    )
}
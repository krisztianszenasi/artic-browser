package com.example.articbrowser.data.remote.dto

import com.example.articbrowser.data.local.entity.ArtEntity
import com.example.articbrowser.domain.Art


fun ArtDto.toArtEntity(): ArtEntity {
    return ArtEntity(
        id = id,
        title = title,
        artistDisplay = artistDisplay,
        imageUrl =  "https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg",
        description = description,
        dateDisplay = dateDisplay,
        artistTitle = artistTitle,
        placeOfOrigin = placeOfOrigin
    )
}

fun ArtDto.toArt(): Art {
    return Art(
        id = id,
        title = title,
        artistDisplay = artistDisplay,
        imageUrl = "https://www.artic.edu/iiif/2/$imageId/full/843,/0/default.jpg",
        description = description,
        publicationHistory = publicationHistory,
        exhibitionHistory = exhibitionHistory,
        dateDisplay = dateDisplay,
        artistTitle = artistTitle ?: "unknown",
        placeOfOrigin = placeOfOrigin
    )
}
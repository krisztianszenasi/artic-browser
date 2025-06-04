package com.example.articbrowser.feature.art_details

import androidx.lifecycle.ViewModel
import coil.network.HttpException
import com.example.articbrowser.data.ResponseWrapper
import com.example.articbrowser.data.local.toArt
import com.example.articbrowser.data.remote.ArtApiService
import com.example.articbrowser.data.remote.RetrofitArtRemoteRepository
import com.example.articbrowser.data.remote.dto.toArtEntity
import com.example.articbrowser.domain.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class ArtDetailsViewModel @Inject constructor(
    private val artRemoteRepository: RetrofitArtRemoteRepository
) : ViewModel(){

    suspend fun fetchArtDetail(id: Int): ResponseWrapper<Art> {
        return artRemoteRepository.fetchArtDetail(id)
    }
}
package com.example.articbrowser.feature.art_grid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.articbrowser.data.local.ArtLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ArtGridViewModel @Inject constructor(
    private val artLocalRepository: ArtLocalRepository
) : ViewModel(){

    private val searchQuery = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalPagingApi::class)
    val artPagingFlow = searchQuery
        .flatMapLatest { query ->
            artLocalRepository.getPagedArtFlow(query)
        }
        .cachedIn(viewModelScope)

    fun search(query: String?) {
        searchQuery.value = if (query == "") null else query
    }
}
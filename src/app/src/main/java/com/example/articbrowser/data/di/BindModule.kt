package com.example.articbrowser.data.di

import com.example.articbrowser.data.local.ArtLocalRepository
import com.example.articbrowser.data.local.PagingArtLocalRepository
import com.example.articbrowser.data.remote.ArtRemoteRepository
import com.example.articbrowser.data.remote.RetrofitArtRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    @Singleton
    abstract fun bindArtRemoteRepository(
        impl: RetrofitArtRemoteRepository
    ): ArtRemoteRepository

    @Binds
    @Singleton
    abstract fun bindArtLocalRepository(
        impl: PagingArtLocalRepository
    ): ArtLocalRepository
}
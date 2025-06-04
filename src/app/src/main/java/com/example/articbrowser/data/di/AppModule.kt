package com.example.articbrowser.data.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.articbrowser.data.local.ArtDatabase
import com.example.articbrowser.data.local.entity.ArtEntity
import com.example.articbrowser.data.remote.ArtApiService
import com.example.articbrowser.data.remote.ArtRemoteMediator
import com.example.articbrowser.data.remote.ArtRemoteRepository
import com.example.articbrowser.data.remote.RetrofitArtRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArtDatabase(@ApplicationContext context: Context): ArtDatabase {
        return Room.databaseBuilder(
            context,
            ArtDatabase::class.java,
            "arts.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArtApiService(): ArtApiService {
        // Create a logging interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY // Logs the body of the request and response

        // Create OkHttpClient with the interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        return Retrofit.Builder()
            .baseUrl(ArtApiService.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideArtPager(artDb: ArtDatabase, artRemoteRepository: ArtRemoteRepository): Pager<Int, ArtEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator =  ArtRemoteMediator(
                artDb = artDb,
                artRemoteRepository = artRemoteRepository
            ),
            pagingSourceFactory = {
                artDb.dao.pagingSource()
            }
        )
    }
}

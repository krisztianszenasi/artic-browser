package com.example.articbrowser.data.remote

import com.example.articbrowser.data.remote.dto.ArtDetailedDto
import com.example.articbrowser.data.remote.dto.ArtDto
import com.example.articbrowser.data.remote.dto.ArtPaginatedDto
import com.example.articbrowser.data.remote.dto.PaginationInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.Test

import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

 class RetrofitArtRemoteRepositoryTest {
  private val apiService = mockk<ArtApiService>()
  private val repository = RetrofitArtRemoteRepository(apiService)
  private val dummyPaginationInfo = PaginationInfo(
    total = 1,
    limit = 1,
    offset = 1,
    totalPages = 1,
    currentPage = 1,
    prevUrl = null,
    nextUrl = null
  )

  @Test
  fun test_fetchArts_success() = runTest {
   val dtoList = listOf(
    ArtDto(
     id = 1,
     title = "Mona Lisa",
     artistDisplay = "da Vinci",
     imageId = "abc")
   )
   coEvery { apiService.fetchArts(any(), any(), any()) } returns ArtPaginatedDto(
    pagination = dummyPaginationInfo,
    data = dtoList
   )
    val result = repository.fetchArts(1, 20)
   assertTrue(result.isSuccessful)
   assertEquals(1, result.data?.size)
   assertEquals("Mona Lisa", result.data?.first()?.title)
  }

  @Test
  fun test_fetchArts_failure() = runTest {
   // Simulate HTTP 404 error
   val errorResponse = Response.error<ArtPaginatedDto>(
    404,
    "Not Found".toResponseBody(null)
   )
   coEvery { apiService.fetchArts(any(), any(), any()) } throws HttpException(errorResponse)

   val result = repository.fetchArts(1, 20)

   // Assert that the result contains the exception
   assertFalse(result.isSuccessful)
   assertNull(result.data)
   assertNotNull(result.exception)
   assertTrue(result.exception is HttpException)
   assertEquals(404, (result.exception as HttpException).code())
  }

  @Test
  fun fetchArtDetail_success() = runTest {
   val dto = ArtDetailedDto(
    data = ArtDto(
     id = 1,
     title = "The Starry Night",
     artistDisplay = "Van Gogh",
     imageId = "xyz"
    )
   )
   coEvery { apiService.fetchArtById(1) } returns dto

   val result = repository.fetchArtDetail(1)

   assertTrue(result.isSuccessful)
   assertEquals("The Starry Night", result.data?.title)
  }

  @Test
  fun fetchArtDetail_failure() = runTest {
   val errorResponse = Response.error<ArtPaginatedDto>(
    404,
    "Not Found".toResponseBody(null)
   )
   coEvery { apiService.fetchArtById(1) } throws HttpException(errorResponse)

   val result = repository.fetchArtDetail(1)

   assertFalse(result.isSuccessful)
   assertNull(result.data)
   assertNotNull(result.exception)
   assertTrue(result.exception is HttpException)
   assertEquals(404, (result.exception as HttpException).code())
  }
}
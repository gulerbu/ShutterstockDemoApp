package com.example.demoapp.testcases

import com.example.demoapp.data.ImagesRemoteRepository
import com.example.demoapp.data.models.Asset
import com.example.demoapp.data.models.AssetList
import com.example.demoapp.data.models.GetImagesResponse
import com.example.demoapp.data.models.Image
import com.example.demoapp.data.network.ResponseHandler
import com.example.demoapp.data.network.Status
import com.example.demoapp.data.source.ImageRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ImageRemoteRepositoryTest {
    @Mock
    private lateinit var dataSource: ImageRemoteDataSource
    private lateinit var responseHandler: ResponseHandler
    private lateinit var repository: ImagesRemoteRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        responseHandler = ResponseHandler()
        repository = ImagesRemoteRepository(dataSource, responseHandler)

    }

    @Test
    fun `given success state, when fetch images called result should be success`() {
        runBlocking {

            `when`(dataSource.fetchImages(ArgumentMatchers.anyInt())).thenReturn(createImagesResponse())
            val response = repository.fetchImages(1)
            assertEquals(response.status, Status.SUCCESS)
        }

    }

    @Test
    fun `given error state, when fetch images called result should be error`() {
        runBlocking {

            `when`(dataSource.fetchImages(ArgumentMatchers.anyInt())).thenReturn(null)
            val response = repository.fetchImages(1)
            assertEquals(response.status, Status.ERROR)
        }

    }


    private fun createImagesResponse(): GetImagesResponse {
        val preview = Asset(
            300,
            "https://image.shutterstock.com/display_pic_with_logo/97565/1309297486/stock-photo-luxury-hotel-home-living-woman-relax-enjoying-sofa-furniture-of-outdoor-patio-beautiful-young-1309297486.jpg",
            450
        )
        val assetList = AssetList(preview)
        val image = Image("1309297486", 1.5, assetList, "Luxury Hotel with beautiful view")

        return GetImagesResponse(1, arrayListOf(image))

    }
}
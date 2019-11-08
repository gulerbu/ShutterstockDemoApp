package com.example.demoapp.testcases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.demoapp.CustomTestCoroutineScope
import com.example.demoapp.data.network.Result
import com.example.demoapp.data.network.Status
import com.example.demoapp.imagelist.ImageListUseCase
import com.example.demoapp.imagelist.ImageListViewModel
import com.example.demoapp.ui.ImageItemUiModel
import com.example.demoapp.ui.ImageListViewState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ImageListViewModelTest {

    @Mock
    private lateinit var useCase: ImageListUseCase
    @Mock
    private lateinit var observer: Observer<ImageListViewState>
    private lateinit var viewModel: ImageListViewModel
    private lateinit var mockImageList: List<ImageItemUiModel>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ImageListViewModel(useCase, CustomTestCoroutineScope())
        mockImageList = createImageList()
    }

    @Test
    fun `given loading state, when fetchImages called, then update live data for loading status`() {

        runBlocking {
            viewModel.imageListViewState.observeForever(observer)

            `when`(useCase.fetchImages(anyInt())).thenReturn(Result.success(mockImageList))

            viewModel.fetchImages(1)

            verify(useCase).fetchImages(anyInt())

            val captor = ArgumentCaptor.forClass(ImageListViewState::class.java)
            verify(observer, times(2)).onChanged(captor.capture())

            //Asserts that first value is loading status
            val state = captor.allValues[0]
            assertEquals(state.status, Status.LOADING)

        }
    }

    @Test
    fun `given success state, when fetchImages called, then update live data for success status and image list`() {
        runBlocking {
            viewModel.imageListViewState.observeForever(observer)

            `when`(useCase.fetchImages(anyInt())).thenReturn(Result.success(mockImageList))

            viewModel.fetchImages(1)

            verify(useCase).fetchImages(anyInt())

            val captor = ArgumentCaptor.forClass(ImageListViewState::class.java)
            verify(observer, times(2)).onChanged(captor.capture())

            //Asserts that first value is loading status
            val state = captor.allValues[1]
            assertEquals(state.status, Status.SUCCESS)
            assertEquals(state.getImages(), mockImageList)

        }
    }

    @Test
    fun `given error state, when fetchImages called, then update live data for error status`() {
        runBlocking {
            viewModel.imageListViewState.observeForever(observer)

            `when`(useCase.fetchImages(anyInt())).thenReturn(Result.error(Exception("Something went wrong !!!")))

            viewModel.fetchImages(1)

            verify(useCase).fetchImages(anyInt())

            val captor = ArgumentCaptor.forClass(ImageListViewState::class.java)
            verify(observer, times(2)).onChanged(captor.capture())

            //Asserts that first value is loading status
            val state = captor.allValues[1]
            assertEquals(state.status, Status.ERROR)

        }
    }


    private fun createMockImage(): ImageItemUiModel {
        return ImageItemUiModel(
            description = "Young Hispanic family sitting on sofa reading a book together in their living room",
            url = "https://image.shutterstock.com/display_pic_with_logo/187633/1284992752/stock-photo-young-hispanic-family-sitting-on-sofa-reading-a-book-together-in-their-living-room-1284992752.jpg"

        )
    }

    private fun createImageList(): List<ImageItemUiModel> {
        val images = mutableListOf<ImageItemUiModel>()
        val mockImage = createMockImage()

        for (x in 0..5) {
            images.add(mockImage)
        }
        return images
    }
}
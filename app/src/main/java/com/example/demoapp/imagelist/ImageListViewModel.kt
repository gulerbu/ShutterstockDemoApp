package com.example.demoapp.imagelist

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.core.BaseViewModel
import com.example.demoapp.coroutines.CustomCoroutineScope
import com.example.demoapp.data.network.Status
import com.example.demoapp.ui.ImageItemUiModel
import com.example.demoapp.ui.ImageListViewState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable

private const val KEY_IMAGE_VIEW_STATE_ERROR = "imageViewStateError"
private const val KEY_IMAGE_VIEW_STATE_DATA = "imageViewStateData"
private const val KEY_IMAGE_VIEW_STATE_STATUS = "imageViewStateStatus"

class ImageListViewModel(
    private val useCase: ImageListUseCase,
    private val coroutineScope: CustomCoroutineScope
) : BaseViewModel() {

    val imageListViewState = MutableLiveData<ImageListViewState>()

    fun fetchImages(page: Int) {
        imageListViewState.value = ImageListViewState(Status.LOADING)

        coroutineScope.launch {
            imageListViewState.value = withContext(coroutineScope.ioDispatcher) {
                val result = useCase.fetchImages(page)
                ImageListViewState(result.status, result.error, result.data)
            }
        }
    }

    fun restoreInstanceSate(savedInstanceState: Bundle) {
        val error = savedInstanceState.getSerializable(KEY_IMAGE_VIEW_STATE_ERROR) as Throwable?
        val data = savedInstanceState.getSerializable(KEY_IMAGE_VIEW_STATE_DATA) as List<ImageItemUiModel>
        val status = savedInstanceState.getSerializable(KEY_IMAGE_VIEW_STATE_STATUS) as Status

        imageListViewState.value = ImageListViewState(status, error, data)

    }

    fun saveInstanceState(outState: Bundle, imageList: MutableList<ImageItemUiModel>) {
        outState.putSerializable(KEY_IMAGE_VIEW_STATE_ERROR, imageListViewState.value?.error as Serializable?)
        outState.putSerializable(KEY_IMAGE_VIEW_STATE_DATA, imageList as Serializable?)
        outState.putSerializable(KEY_IMAGE_VIEW_STATE_STATUS, imageListViewState.value?.status)

    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }
}
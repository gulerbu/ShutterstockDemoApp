package com.example.demoapp.imagelist

import com.example.demoapp.coroutines.CustomCoroutineScope
import com.example.demoapp.data.ImagesApi
import com.example.demoapp.data.ImagesRemoteRepository
import com.example.demoapp.data.ImagesRepository
import com.example.demoapp.data.network.ResponseHandler
import com.example.demoapp.data.source.ImageDataSource
import com.example.demoapp.data.source.ImageRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageListModule = module {
    single { provideImageDataSource(get()) }
    single { provideImagesRepository(get(), get()) }
    viewModel { provideViewModel(get(), get()) }
    factory { provideUseCase(get()) }
    factory { provideListAdapter() }
}

fun provideImageDataSource(api: ImagesApi): ImageDataSource = ImageRemoteDataSource(api)

fun provideImagesRepository(dataSource: ImageDataSource, responseHandler: ResponseHandler): ImagesRepository {
    return ImagesRemoteRepository(dataSource, responseHandler)
}

fun provideUseCase(repository: ImagesRepository) = ImageListUseCase(repository)

fun provideViewModel(useCase: ImageListUseCase, coroutineScope: CustomCoroutineScope) = ImageListViewModel(useCase, coroutineScope)

fun provideListAdapter() = ImageListAdapter()
package com.example.demoapp.data.network

import com.example.demoapp.data.ImagesApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideImagesApi(get()) }
    factory { provideResponseHandler() }
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.shutterstock.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun provideImagesApi(retrofit: Retrofit): ImagesApi {
    return retrofit.create(ImagesApi::class.java)
}

fun provideResponseHandler() = ResponseHandler()


package com.example.demoapp.core

import android.app.Application
import com.example.demoapp.coroutines.coroutineModule
import com.example.demoapp.data.network.networkModule
import com.example.demoapp.imagelist.imageListModule
import org.koin.core.context.startKoin

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            modules(listOf(networkModule, imageListModule, coroutineModule))
        }
    }

    companion object {
        lateinit var instance: DemoApp
    }
}
package com.example.demoapp.coroutines

import org.koin.dsl.module

val coroutineModule = module {
    factory { CustomCoroutineScope() }
}

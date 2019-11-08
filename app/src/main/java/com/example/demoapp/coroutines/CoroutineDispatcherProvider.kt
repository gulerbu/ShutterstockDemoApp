package com.example.demoapp.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutineDispatcherProvider(
    val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    val IODispatcher: CoroutineDispatcher = Dispatchers.IO
)
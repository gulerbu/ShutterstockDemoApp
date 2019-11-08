package com.example.demoapp

import com.example.demoapp.coroutines.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : CoroutineDispatcherProvider(mainDispatcher = Dispatchers.Unconfined,
    IODispatcher = Dispatchers.Unconfined
)

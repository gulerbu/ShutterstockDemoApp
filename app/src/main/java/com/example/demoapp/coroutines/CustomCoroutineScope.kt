package com.example.demoapp.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class CustomCoroutineScope(
    job: Job = Job(),
    dispatcherProvider: CoroutineDispatcherProvider = CoroutineDispatcherProvider()
) : CoroutineScope {

    val ioDispatcher = dispatcherProvider.IODispatcher

    override val coroutineContext: CoroutineContext = job + dispatcherProvider.mainDispatcher
}
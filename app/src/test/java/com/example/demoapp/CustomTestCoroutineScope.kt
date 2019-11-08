package com.example.demoapp

import com.example.demoapp.coroutines.CustomCoroutineScope
import kotlinx.coroutines.Job

class CustomTestCoroutineScope: CustomCoroutineScope(Job(), TestDispatcherProvider())

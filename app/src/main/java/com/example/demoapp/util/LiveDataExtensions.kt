package com.example.demoapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, action: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(action)
    })
}

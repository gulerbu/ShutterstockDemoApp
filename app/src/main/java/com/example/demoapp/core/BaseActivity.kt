package com.example.demoapp.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(clazz: KClass<VM>) : AppCompatActivity() {

    val viewModel: VM by viewModel(clazz)

    open lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getContentLayoutResourceId())

        initUserInterface()
        attachViewModelObservers()

    }

    open fun initUserInterface() {

    }

    abstract fun attachViewModelObservers()

    @LayoutRes
    abstract fun getContentLayoutResourceId(): Int

}
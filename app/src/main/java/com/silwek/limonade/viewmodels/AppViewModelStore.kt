package com.silwek.limonade.viewmodels

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

object AppViewModelStore: ViewModelStoreOwner {
    private val viewModelStore:ViewModelStore = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore = viewModelStore
}
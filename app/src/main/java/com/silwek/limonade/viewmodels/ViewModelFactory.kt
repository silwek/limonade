package com.silwek.limonade.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SliceViewModel::class.java) -> {
                SliceViewModel(context) as T
            }

            modelClass.isAssignableFrom(SliceConfigViewModel::class.java) -> {
                SliceConfigViewModel(context) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        operator fun <T : ViewModel?> get(context: Context, modelClass: Class<out T>): T {
            return with(context)[modelClass]
        }

        private fun with(context: Context): ViewModelProvider {
            return ViewModelProvider(AppViewModelStore, ViewModelFactory(context))
        }
    }
}

fun Activity.getSliceViewModel() =
    ViewModelFactory.get(applicationContext, SliceViewModel::class.java)

fun Activity.getSliceConfigViewModel() =
    ViewModelFactory.get(applicationContext, SliceConfigViewModel::class.java)

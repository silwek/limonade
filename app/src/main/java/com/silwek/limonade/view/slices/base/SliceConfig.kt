package com.silwek.limonade.view.slices.base

import android.view.LayoutInflater
import android.view.ViewGroup

interface SliceConfig {
    val key: String
    fun inflateFormView(inflater: LayoutInflater, parent: ViewGroup?): SliceFormView
}
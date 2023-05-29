package com.silwek.limonade.view.slices.base

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.Slice
import com.silwek.limonade.models.SliceConfig

interface BaseSliceType {
    val key: String
    val config: SliceConfig
    fun inflateFormView(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SliceFormView

    fun fromEntityToModel(sliceEntity: SliceEntity): Slice
    fun toString(slice: Slice): String
}
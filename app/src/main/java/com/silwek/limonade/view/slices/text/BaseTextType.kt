package com.silwek.limonade.view.slices.text

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.databinding.ViewSliceTextFormBinding
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.Slice
import com.silwek.limonade.models.StringSlice
import com.silwek.limonade.view.slices.base.BaseSliceType
import com.silwek.limonade.view.slices.base.SliceFormView

abstract class BaseTextType: BaseSliceType {
    override fun inflateFormView(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SliceFormView {
        val binding = ViewSliceTextFormBinding.inflate(inflater, parent, false).root
        binding.viewType = config.key
        binding.setLabel(config.label)
        return binding
    }

    override fun fromEntityToModel(sliceEntity: SliceEntity): Slice {
        return StringSlice.fromEntityToModel(sliceEntity)
    }
}
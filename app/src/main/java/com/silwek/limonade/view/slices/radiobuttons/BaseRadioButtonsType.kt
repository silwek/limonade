package com.silwek.limonade.view.slices.radiobuttons

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.databinding.ViewSliceRadiobuttonsFormBinding
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.BaseSliceType
import com.silwek.limonade.view.slices.base.SliceFormView

abstract class BaseRadioButtonsType : BaseSliceType {
    override fun inflateFormView(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SliceFormView {
        val binding = ViewSliceRadiobuttonsFormBinding.inflate(inflater, parent, false).root
        binding.viewType = config.key
        binding.setLabel(config.label)
        return binding
    }

    fun configToLabels(config: String): Array<String> {
        return config.split(",").map { it.replace("&vir;", ",") }.toTypedArray()
    }

    fun labelsToConfig(labels: Array<String>): String {
        return labels.joinToString(",") { it.replace(",", "&vir;") }
    }

    override fun fromEntityToModel(sliceEntity: SliceEntity): Slice {
        return IntSlice.fromEntityToModel(sliceEntity)
    }
}
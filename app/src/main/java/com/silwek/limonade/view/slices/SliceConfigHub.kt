package com.silwek.limonade.view.slices

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView
import com.silwek.limonade.view.slices.mood.MoodSlice
import com.silwek.limonade.view.slices.period.PeriodSlice

object SliceConfigHub {
    private val configs = ArrayList<SliceConfig>()

    init {
        //Order slice types registration according to display wanted order
        register(MoodSlice)
        register(PeriodSlice)
    }

    fun register(config: SliceConfig) {
        if (!configs.contains(config))
            configs.add(config)
    }

    fun getConfigs(): List<SliceConfig> {
        return configs.toList()
    }

    fun getConfig(slice: Slice): SliceConfig? {
        return configs.firstOrNull { it.key == slice.getSliceType() }
    }

    fun getConfig(key: String): SliceConfig? {
        return configs.firstOrNull { it.key == key }
    }

    fun getFormView(inflater: LayoutInflater, parent: ViewGroup?, slice: Slice): SliceFormView? {
        return getConfig(slice)?.inflateFormView(inflater, parent)
    }

    fun fromEntityToModel(sliceEntity: SliceEntity): Slice? {
        val config = getConfig(sliceEntity.key)
        return config?.fromEntityToModel(sliceEntity)
    }
}
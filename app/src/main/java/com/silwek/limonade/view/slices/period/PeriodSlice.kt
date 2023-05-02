package com.silwek.limonade.view.slices.period

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.databinding.ViewSlicePeriodFormBinding
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView

object PeriodSlice : SliceConfig {
    override val key: String = "period"

    override fun inflateFormView(inflater: LayoutInflater, parent: ViewGroup?): SliceFormView {
        return ViewSlicePeriodFormBinding.inflate(inflater, parent, false).root
    }

    override fun fromEntityToModel(sliceEntity: SliceEntity): Slice {
        return IntSlice.fromEntityToModel(sliceEntity)
    }

    override fun toString(slice: Slice): String {
        if (slice !is IntSlice) return ""
        return "Mes règles : ${valueToString(slice.value)}"
    }

    private fun valueToString(value: Int?): String {
        return when (value) {
            0 -> "❌"
            1 -> "🩸"
            2 -> "🩸🩸"
            3 -> "🩸🩸🩸"
            else -> ""
        }
    }
}
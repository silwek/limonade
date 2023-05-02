package com.silwek.limonade.view.slices.mood

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.databinding.ViewSliceMoodFormBinding
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView

object MoodSlice : SliceConfig {
    override val key: String = "mood"
    override fun inflateFormView(inflater: LayoutInflater, parent: ViewGroup?): SliceFormView {
        return ViewSliceMoodFormBinding.inflate(inflater, parent, false).root
    }

    override fun fromEntityToModel(sliceEntity: SliceEntity): Slice {
        return IntSlice.fromEntityToModel(sliceEntity)
    }

    override fun toString(slice: Slice): String {
        if (slice !is IntSlice) return ""
        return "Mon humeur : ${
            valueToString(slice.value)
        }"
    }

    private fun valueToString(value: Int?): String {
        return when (value) {
            1 -> "😵‍💫"
            2 -> "😞"
            3 -> "😐"
            4 -> "🙂"
            5 -> "🤪"
            else -> ""
        }
    }
}
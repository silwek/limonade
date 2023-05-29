package com.silwek.limonade.view.slices.text

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.models.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView
import com.silwek.limonade.view.slices.radiobuttons.BaseRadioButtonsType
import com.silwek.limonade.view.slices.radiobuttons.RadioButtonsView

class TextType(override val config: SliceConfig) : BaseTextType() {
    override val key: String = VIEW_TYPE
    private var mainLabel: String = ""

    override fun inflateFormView(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SliceFormView {
        val binding =
            super.inflateFormView(inflater, parent) as RadioButtonsView
        mainLabel = config.label
        return binding
    }

    override fun toString(slice: Slice): String {
        if (slice !is IntSlice) return ""
        return "$mainLabel : ${slice.value}"
    }

    companion object {
        const val VIEW_TYPE = "text"
    }
}
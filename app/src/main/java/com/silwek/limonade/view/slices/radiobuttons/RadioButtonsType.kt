package com.silwek.limonade.view.slices.radiobuttons

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.models.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView

class RadioButtonsType(override val config: SliceConfig) : BaseRadioButtonsType() {
    override val key: String = VIEW_TYPE
    private var labels: Array<String> = emptyArray()

    override fun inflateFormView(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): SliceFormView {
        val binding =
            super.inflateFormView(inflater, parent) as RadioButtonsView
        binding.showButtons(getLabels())
        return binding
    }

    override fun toString(slice: Slice): String {
        if (slice !is IntSlice) return ""
        return "${config.label} : ${
            getLabels().getOrElse(slice.value ?: -1, { "N/D" })
        }"
    }



    private fun getLabels(): Array<String> {
        if(labels.isEmpty()){
            labels = configToLabels(config.config)
        }
        return labels
    }

    companion object {
        const val VIEW_TYPE = "radiobuttons"
    }
}
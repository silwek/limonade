package com.silwek.limonade.view.slices.period

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.databinding.ViewSliceMoodFormBinding
import com.silwek.limonade.databinding.ViewSlicePeriodFormBinding
import com.silwek.limonade.view.slices.base.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView

object PeriodSlice : SliceConfig {
    override val key: String = "period"

    override fun inflateFormView(inflater: LayoutInflater, parent: ViewGroup?): SliceFormView {
        return ViewSlicePeriodFormBinding.inflate(inflater, parent, false).root
    }
}
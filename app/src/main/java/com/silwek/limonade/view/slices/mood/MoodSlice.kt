package com.silwek.limonade.view.slices.mood

import android.view.LayoutInflater
import android.view.ViewGroup
import com.silwek.limonade.databinding.ViewSliceMoodFormBinding
import com.silwek.limonade.view.slices.base.SliceConfig
import com.silwek.limonade.view.slices.base.SliceFormView

object MoodSlice : SliceConfig {
    override val key: String = "mood"
    override fun inflateFormView(inflater: LayoutInflater, parent: ViewGroup?): SliceFormView {
        return ViewSliceMoodFormBinding.inflate(inflater, parent, false).root
    }
}
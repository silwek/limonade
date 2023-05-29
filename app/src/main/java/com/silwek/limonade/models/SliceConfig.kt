package com.silwek.limonade.models

import com.silwek.limonade.view.slices.base.BaseSliceType

abstract class SliceConfig(
    var key: String? = null,
    var viewtype: String,
    var label: String,
    var config: String
) {
    abstract fun getViewTypeBuilder(): BaseSliceType
}
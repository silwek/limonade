package com.silwek.limonade.view.slices.base

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.silwek.limonade.models.Slice
import java.time.LocalDate

abstract class SliceFormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        orientation = VERTICAL
    }

    var day: LocalDate? = null
    var viewType: String? = null
    var onUpdate: ((Slice) -> Unit)? = null

    abstract fun setLabel(label: String)

    abstract fun setSlice(slice: Slice?)

    abstract fun getSlice(): Slice

}
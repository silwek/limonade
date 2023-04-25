package com.silwek.limonade.view.slices.base

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.silwek.limonade.models.Slice
import java.time.LocalDate

open class SliceFormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        orientation = VERTICAL
    }

    var day: LocalDate? = null
    var onUpdate:((Slice)->Unit)?=null

    open fun setSlice(slice: Slice?) {
        TODO("Not yet implemented")
    }

    open fun getSlice(): Slice {
        TODO("Not yet implemented")
    }

}
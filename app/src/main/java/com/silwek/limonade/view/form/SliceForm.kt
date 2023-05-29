package com.silwek.limonade.view.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.view.children
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.BaseSliceType
import com.silwek.limonade.view.slices.base.SliceFormView

class SliceForm @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ScrollView(context, attrs) {

    var sliceContainer: LinearLayout? = null

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        sliceContainer = getChildAt(0) as LinearLayout?
    }

    fun buildForm(sliceTypesConfig: List<BaseSliceType>) {
        if (sliceContainer == null) {
            sliceContainer = getChildAt(0) as LinearLayout?
        }
        val container = sliceContainer ?: return
        if (container.childCount > 0) return
        val layoutInflater = LayoutInflater.from(context)
        sliceTypesConfig.forEach { sliceTypes ->
            container.addView(
                sliceTypes.inflateFormView(
                    layoutInflater,
                    container
                )
            )
        }
    }

    fun getSlices(): List<Slice> {
        val slices = ArrayList<Slice>()
        val container = sliceContainer ?: return slices
        container.children.forEach {
            if (it is SliceFormView) {
                slices.add(it.getSlice())
            }
        }
        return slices
    }

}
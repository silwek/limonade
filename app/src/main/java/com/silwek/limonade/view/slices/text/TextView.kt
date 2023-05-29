package com.silwek.limonade.view.slices.text

import android.content.Context
import android.util.AttributeSet
import com.silwek.limonade.databinding.ViewSliceTextFormBinding
import com.silwek.limonade.models.Slice
import com.silwek.limonade.models.StringSlice
import com.silwek.limonade.view.slices.base.SliceFormView
import java.time.LocalDate

class TextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SliceFormView(context, attrs) {

    private lateinit var binding: ViewSliceTextFormBinding

    private var slice: StringSlice? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewSliceTextFormBinding.bind(this)
    }

    override fun setLabel(label: String) {
        binding.label.text = label
    }

    override fun setSlice(slice: Slice?) {
        if (slice !is StringSlice?) return
        this.slice = slice
        displaySlice()
    }

    private fun displaySlice() {
        if (slice == null) {
            binding.textfield.setText("")
        } else {
            binding.textfield.setText(slice?.value)
        }
    }

    override fun getSlice(): Slice {
        return StringSlice(
            id = slice?.id,
            day = slice?.day ?: day ?: LocalDate.now(),
            slice?.type ?: viewType?: "",
            binding.textfield.text.toString() ?: ""
        )
    }
}
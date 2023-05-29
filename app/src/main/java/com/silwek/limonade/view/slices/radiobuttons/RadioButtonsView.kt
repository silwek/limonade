package com.silwek.limonade.view.slices.radiobuttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.silwek.limonade.R
import com.silwek.limonade.databinding.ViewSliceRadiobuttonsFormBinding
import com.silwek.limonade.databinding.ViewSliceRadiobuttonsItemFormBinding
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.SliceFormView
import java.time.LocalDate

class RadioButtonsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SliceFormView(context, attrs) {

    private lateinit var binding: ViewSliceRadiobuttonsFormBinding

    private var slice: IntSlice? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewSliceRadiobuttonsFormBinding.bind(this)
    }

    override fun setLabel(label: String) {
        binding.label.text = label
    }

    fun showButtons(labels: Array<String>) {
        if (binding.radioGroup.childCount > 0) return
        if (context == null) return
        val layoutInflater = LayoutInflater.from(context)
        labels.forEach { label ->
            addButton(label, layoutInflater)
        }
    }

    private fun addButton(label: String, layoutInflater: LayoutInflater) {
        val button =
            ViewSliceRadiobuttonsItemFormBinding.inflate(layoutInflater, binding.radioGroup, false)
        button.root.text = label
        binding.radioGroup.addView(button.root, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))
    }

    override fun setSlice(slice: Slice?) {
        if (slice !is IntSlice?) return
        this.slice = slice
        displaySlice()
        binding.radioGroup.addOnButtonCheckedListener { _, _, _ ->
            onUpdate?.invoke(getSlice())
        }
    }

    private fun displaySlice() {
        if (slice == null) {
            binding.radioGroup.clearChecked()
        } else {
            val value = slice?.value
            val count = binding.radioGroup.childCount
            if (value != null && value in 0 until count) {
                binding.radioGroup.check(binding.radioGroup.getChildAt(value).id)
            } else {
                binding.radioGroup.clearChecked()
            }
        }
    }

    override fun getSlice(): Slice {
        val id = binding.radioGroup.checkedButtonId
        val count = binding.radioGroup.childCount
        var value: Int? = null
        if (id != NO_ID) {
            for (i in 0 until count) {
                if (binding.radioGroup.getChildAt(i).id == id) {
                    value = i
                    break
                }
            }
        }
        return IntSlice(
            id = slice?.id,
            day = slice?.day ?: day ?: LocalDate.now(),
            slice?.type ?: viewType?: "",
            value
        )
    }
}
package com.silwek.limonade.view.slices.period

import android.content.Context
import android.util.AttributeSet
import com.silwek.limonade.R
import com.silwek.limonade.databinding.ViewSlicePeriodFormBinding
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.SliceFormView
import java.time.LocalDate

class PeriodSliceFormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SliceFormView(context, attrs) {

    private lateinit var binding: ViewSlicePeriodFormBinding

    private var slice: IntSlice? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewSlicePeriodFormBinding.bind(this)
    }

    override fun setSlice(slice: Slice?) {
        if (slice !is IntSlice?) return
        this.slice = slice
        displaySlice()
        binding.bloodScale.addOnButtonCheckedListener { _, _, _ ->
            onUpdate?.invoke(getSlice())
        }
    }

    private fun displaySlice() {
        if (slice == null) {
            binding.bloodScale.clearChecked()
        } else {
            val value = slice?.value
            if (value in 0..3) {
                binding.bloodScale.check(
                    when (value) {
                        0 -> R.id.blood0
                        1 -> R.id.blood1
                        2 -> R.id.blood2
                        else -> R.id.blood3
                    }
                )
            } else {
                binding.bloodScale.clearChecked()
            }
        }
    }

    override fun getSlice(): Slice {
        val bloodflow = when (binding.bloodScale.checkedButtonId) {
            R.id.blood0 -> 0
            R.id.blood1 -> 1
            R.id.blood2 -> 2
            R.id.blood3 -> 3
            else -> null
        }
        return IntSlice(
            id = slice?.id,
            day = slice?.day ?: day ?: LocalDate.now(),
            PeriodSlice.key,
            bloodflow
        )
    }
}
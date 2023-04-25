package com.silwek.limonade.view.slices.mood

import android.content.Context
import android.util.AttributeSet
import com.silwek.limonade.R
import com.silwek.limonade.databinding.ViewSliceMoodFormBinding
import com.silwek.limonade.models.IntSlice
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.base.SliceFormView
import java.time.LocalDate

class MoodSliceFormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SliceFormView(context, attrs) {

    private lateinit var binding: ViewSliceMoodFormBinding

    private var slice: IntSlice? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewSliceMoodFormBinding.bind(this)
    }

    override fun setSlice(slice: Slice?) {
        if (slice !is IntSlice?) return
        this.slice = slice
        displaySlice()
        binding.moodScale.addOnButtonCheckedListener { _, _, _ ->
            onUpdate?.invoke(getSlice())
        }
    }

    private fun displaySlice() {
        if (slice == null) {
            binding.moodScale.clearChecked()
        } else {
            val value = slice?.value
            if (value in 1..5) {
                binding.moodScale.check(
                    when (value) {
                        1 -> R.id.mood1
                        2 -> R.id.mood2
                        3 -> R.id.mood3
                        4 -> R.id.mood4
                        else -> R.id.mood5
                    }
                )
            } else {
                binding.moodScale.clearChecked()
            }
        }
    }

    override fun getSlice(): Slice {
        val mood = when (binding.moodScale.checkedButtonId) {
            R.id.mood1 -> 1
            R.id.mood2 -> 2
            R.id.mood3 -> 3
            R.id.mood4 -> 4
            R.id.mood5 -> 5
            else -> null
        }
        return IntSlice(
            id = slice?.id,
            day = slice?.day ?: day ?: LocalDate.now(),
            MoodSlice.key,
            mood
        )
    }
}
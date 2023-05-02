package com.silwek.limonade.view.list

import androidx.recyclerview.widget.RecyclerView
import com.silwek.limonade.databinding.ViewDaysDataBinding
import com.silwek.limonade.models.DaySlices
import java.time.format.DateTimeFormatter

class DayListViewHolder(private var binding: ViewDaysDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(daySlice: DaySlices) {
        binding.dayDate.text = daySlice.date.format(DateTimeFormatter.ISO_DATE)
        var str = ""
        daySlice.slices.forEach {
            if (str.isNotEmpty()) {
                str += "\n"
            }
            str += it.getConfig()?.toString(it)
        }
        binding.data.text = str
    }
}
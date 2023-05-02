package com.silwek.limonade.view.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silwek.limonade.databinding.ViewDaysDataBinding
import com.silwek.limonade.models.DaySlices

class DayListAdapter(context: Context) : RecyclerView.Adapter<DayListViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)
    var daysSlices: List<DaySlices> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayListViewHolder {
        return DayListViewHolder(ViewDaysDataBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: DayListViewHolder, position: Int) {
        holder.bind(daysSlices[position])
    }

    override fun getItemCount(): Int {
        return daysSlices.count()
    }
}
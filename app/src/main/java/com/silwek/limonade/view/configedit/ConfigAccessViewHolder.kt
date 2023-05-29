package com.silwek.limonade.view.configedit

import androidx.recyclerview.widget.RecyclerView
import com.silwek.limonade.databinding.ItemConfigNameBinding
import com.silwek.limonade.databinding.ViewDaysDataBinding
import com.silwek.limonade.models.DaySlices
import com.silwek.limonade.models.SliceConfig
import java.time.format.DateTimeFormatter

class ConfigAccessViewHolder(private var binding: ItemConfigNameBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(config: SliceConfig) {
        binding.configName.text = config.label
    }
}
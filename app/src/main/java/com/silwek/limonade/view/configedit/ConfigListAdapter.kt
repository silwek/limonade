package com.silwek.limonade.view.configedit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silwek.limonade.databinding.ItemConfigNameBinding
import com.silwek.limonade.models.SliceConfig

class ConfigListAdapter(context: Context) : RecyclerView.Adapter<ConfigAccessViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)
    var sliceConfigs: List<SliceConfig> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigAccessViewHolder {
        return ConfigAccessViewHolder(ItemConfigNameBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ConfigAccessViewHolder, position: Int) {
        holder.bind(sliceConfigs[position])
    }

    override fun getItemCount(): Int {
        return sliceConfigs.count()
    }
}
package com.silwek.limonade.view.list

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.silwek.limonade.databinding.ActivityListBinding
import com.silwek.limonade.models.DaySlices
import com.silwek.limonade.viewmodels.SliceViewModel
import com.silwek.limonade.viewmodels.getSliceViewModel

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: DayListAdapter
    private val slicesViewModel: SliceViewModel by lazy { getSliceViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = DayListAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        binding.data.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.data.adapter = adapter
        slicesViewModel.dayslices.observe(this, this::onData)
    }

    override fun onResume() {
        super.onResume()
        slicesViewModel.loadAllSlices()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onData(daySlices: List<DaySlices>?) {
        if (daySlices != null) {
            adapter.daysSlices = daySlices.toList()
            adapter.notifyDataSetChanged()
        }
    }
}
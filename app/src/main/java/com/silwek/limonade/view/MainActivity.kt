package com.silwek.limonade.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.silwek.limonade.databinding.ActivityMainBinding
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.form.FormActivity
import com.silwek.limonade.view.list.ListActivity
import com.silwek.limonade.viewmodels.SliceViewModel
import com.silwek.limonade.viewmodels.getSliceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val slicesViewModel: SliceViewModel by lazy { getSliceViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
        binding.button.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        slicesViewModel.slices.observe(this, this::onSlices)
    }

    override fun onResume() {
        super.onResume()
        slicesViewModel.loadSlices()
    }

    private fun onSlices(slices: List<Slice>?) {
        var str = ""
        slices?.forEach {
            if (str.isNotEmpty()) {
                str += "\n"
            }
            str += it.getConfig()?.toString(it)
        }
        binding.debug.text = str
    }
}
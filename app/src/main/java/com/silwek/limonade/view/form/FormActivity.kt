package com.silwek.limonade.view.form

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.silwek.limonade.databinding.ActivityFormBinding
import com.silwek.limonade.models.SliceConfig
import com.silwek.limonade.viewmodels.SliceViewModel
import com.silwek.limonade.viewmodels.getSliceViewModel

//TODO ALlow to change the day of the report
class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private val slicesViewModel: SliceViewModel by lazy { getSliceViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fab.setOnClickListener { view ->
            val slices = binding.sliceForm.getSlices()
            Log.v("FormActivity", "Slices : $slices")
            slicesViewModel.addSlices(slices)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        slicesViewModel.sliceConfigs.observe(this, this::onConfigs)
    }

    private fun onConfigs(sliceConfigs: List<SliceConfig>?) {
        binding.sliceForm.buildForm(sliceConfigs?.map { it.getViewTypeBuilder() }
            ?: emptyList())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
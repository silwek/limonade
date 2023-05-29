package com.silwek.limonade.view.configedit

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.silwek.limonade.databinding.ActivityConfigListBinding
import com.silwek.limonade.models.SliceConfig
import com.silwek.limonade.view.form.FormActivity
import com.silwek.limonade.viewmodels.SliceConfigViewModel
import com.silwek.limonade.viewmodels.getSliceConfigViewModel

class ConfigListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigListBinding
    private lateinit var adapter: ConfigListAdapter
    private val sliceConfigsViewModel: SliceConfigViewModel by lazy { getSliceConfigViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfigListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = ConfigListAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        binding.configs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.configs.adapter = adapter
        sliceConfigsViewModel.sliceConfigs.observe(this, this::onData)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, ConfigEditActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        sliceConfigsViewModel.refreshConfigs()
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

    private fun onData(sliceConfigs: List<SliceConfig>?) {
        if (sliceConfigs != null) {
            adapter.sliceConfigs = sliceConfigs.toList()
            adapter.notifyDataSetChanged()
        }
    }

}
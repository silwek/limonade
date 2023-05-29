package com.silwek.limonade.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.silwek.limonade.R
import com.silwek.limonade.databinding.ActivityMainBinding
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.configedit.ConfigListActivity
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, ConfigListActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        slicesViewModel.refreshConfigs()
        slicesViewModel.slices.observe(this, this::onSlices)
    }

    override fun onResume() {
        super.onResume()
        slicesViewModel.loadSlices()
    }

    private fun onSlices(slices: List<Slice>?) {
        var str = ""
        val configs = slicesViewModel.getConfigForSlices(slices ?: emptyList())
        slices?.forEachIndexed { index, slice ->
            if (str.isNotEmpty()) {
                str += "\n"
            }
            val config = configs[index]
            str += config?.getViewTypeBuilder()?.toString(slice)
        }
        binding.debug.text = str
    }
}
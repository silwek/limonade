package com.silwek.limonade.view.form

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.silwek.limonade.databinding.ActivityFormBinding
import com.silwek.limonade.view.slices.SliceConfigHub

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fab.setOnClickListener { view ->
            val slices = binding.sliceForm.getSlices()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            Log.v("FormActivity", "Slices : $slices")
        }
    }

    override fun onStart() {
        super.onStart()
        binding.sliceForm.buildForm(SliceConfigHub.getConfigs())
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
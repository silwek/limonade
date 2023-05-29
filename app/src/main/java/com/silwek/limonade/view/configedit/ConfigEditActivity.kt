package com.silwek.limonade.view.configedit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.silwek.limonade.databinding.ActivityConfigFormBinding
import com.silwek.limonade.viewmodels.SliceConfigViewModel
import com.silwek.limonade.viewmodels.getSliceConfigViewModel

class ConfigEditActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityConfigFormBinding
    private val sliceConfigsViewModel: SliceConfigViewModel by lazy { getSliceConfigViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfigFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
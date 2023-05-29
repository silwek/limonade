package com.silwek.limonade.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silwek.limonade.datasources.SliceConfigRoomRepository
import com.silwek.limonade.models.SliceConfig
import kotlinx.coroutines.launch

//TODO action for editing slice configs
class SliceConfigViewModel(context: Context) : ViewModel() {
    private val repository = SliceConfigRoomRepository(context)
    private val _sliceConfigs: MutableLiveData<List<SliceConfig>> = MutableLiveData()
    val sliceConfigs: LiveData<List<SliceConfig>> = _sliceConfigs

    fun refreshConfigs() {
        viewModelScope.launch {
            repository.loadAllConfig {
                _sliceConfigs.value = it.toList()
            }
        }
    }


}
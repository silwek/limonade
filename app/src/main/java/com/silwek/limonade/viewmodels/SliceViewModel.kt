package com.silwek.limonade.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silwek.limonade.datasources.SliceConfigRoomRepository
import com.silwek.limonade.datasources.SliceRoomRepository
import com.silwek.limonade.models.DaySlices
import com.silwek.limonade.models.Slice
import com.silwek.limonade.models.SliceConfig
import kotlinx.coroutines.launch
import java.time.LocalDate

class SliceViewModel(context: Context) : ViewModel() {


    private val configRepository = SliceConfigRoomRepository(context)
    private val _sliceConfigs: MutableLiveData<List<SliceConfig>> = MutableLiveData()
    val sliceConfigs: LiveData<List<SliceConfig>> = _sliceConfigs

    private val repository = SliceRoomRepository(context)
    private val _slices: MutableLiveData<MutableList<Slice>> = MutableLiveData()
    val slices: LiveData<MutableList<Slice>> = _slices
    private val _dayslices: MutableLiveData<MutableList<DaySlices>> = MutableLiveData()
    val dayslices: LiveData<MutableList<DaySlices>> = _dayslices
    var day: LocalDate = LocalDate.now()

    fun refreshConfigs() {
        viewModelScope.launch {
            configRepository.loadAllConfig {
                _sliceConfigs.value = it.toList()
            }
        }
    }

    fun getConfigForSlices(slices: List<Slice>): List<SliceConfig?> {
        val configs = _sliceConfigs.value ?: emptyList()
        return slices.map {
            val key = it.getSliceType()
            configs.firstOrNull { config -> config.key == key }
        }
    }

    fun loadSlices() {
        viewModelScope.launch {
            repository.loadSlices(day) {
                _slices.value = it.toMutableList()
            }
        }
    }

    fun addSlices(slices: List<Slice>) {
        viewModelScope.launch {
            repository.addSlices(slices) {
                loadSlices()
            }
        }
    }

    fun loadAllSlices() {
        viewModelScope.launch {
            repository.loadAllSlices {
                sortDaySlices(it.toMutableList())
            }
        }
    }

    private fun sortDaySlices(slices: MutableList<Slice>) {
        if (slices.size == 0) {
            _dayslices.value = emptyList<DaySlices>().toMutableList()
            return
        }
        _dayslices.value = slices.groupBy {
            it.day
        }.map {
            DaySlices(
                date = it.key,
                slices = it.value,
                slicesConfigs = getConfigForSlices(it.value)
            )
        }.toMutableList()
    }

}
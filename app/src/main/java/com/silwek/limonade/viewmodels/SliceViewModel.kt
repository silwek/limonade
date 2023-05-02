package com.silwek.limonade.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silwek.limonade.datasources.SliceRoomRepository
import com.silwek.limonade.models.DaySlices
import com.silwek.limonade.models.Slice
import kotlinx.coroutines.launch
import java.time.LocalDate

class SliceViewModel(context: Context) : ViewModel() {

    private val repository = SliceRoomRepository(context)
    private val _slices: MutableLiveData<MutableList<Slice>> = MutableLiveData()
    val slices: LiveData<MutableList<Slice>> = _slices
    private val _dayslices: MutableLiveData<MutableList<DaySlices>> = MutableLiveData()
    val dayslices: LiveData<MutableList<DaySlices>> = _dayslices
    var day: LocalDate = LocalDate.now()

    fun loadSlices() {
        viewModelScope.launch {
            repository.loadSlices(day) {
                _slices.value = it.toMutableList()
            }
        }
    }

    fun addSlices(slices: List<Slice>) {
        //TODO check existing slice for each key to replace them
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
                slices = it.value
            )
        }.toMutableList()
    }

}
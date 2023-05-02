package com.silwek.limonade.datasources

import android.content.Context
import androidx.room.Room
import com.silwek.limonade.datasources.room.RoomAppDatabase
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.models.Slice
import com.silwek.limonade.view.slices.SliceConfigHub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

interface SliceRepository {

    suspend fun loadAllSlices(onSuccess: (List<Slice>) -> Unit)
    suspend fun loadSlices(day: LocalDate, onSuccess: (List<Slice>) -> Unit)
    suspend fun addSlices(slices: List<Slice>, onSuccess: () -> Unit)
    suspend fun updateSlices(slices: List<Slice>, onSuccess: () -> Unit)
    suspend fun deleteSlices(slices: List<Slice>, onSuccess: () -> Unit)
}

class SliceRoomRepository(context: Context) : SliceRepository {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        RoomAppDatabase::class.java, "limonade-name"
    ).build()
    private val sliceDao by lazy { db.sliceDao() }


    override suspend fun loadAllSlices(onSuccess: (List<Slice>) -> Unit) {
        withContext(Dispatchers.IO) {
            val slicesEntities = sliceDao.getAll()
            val slices = slicesEntities.mapNotNull { SliceConfigHub.fromEntityToModel(it) }
            withContext(Dispatchers.Main) {
                onSuccess(slices)
            }
        }
    }

    override suspend fun loadSlices(day: LocalDate, onSuccess: (List<Slice>) -> Unit) {
        withContext(Dispatchers.IO) {
            val slicesEntities = sliceDao.getAllForDay(day)
            val slices = slicesEntities.mapNotNull { SliceConfigHub.fromEntityToModel(it) }
            withContext(Dispatchers.Main) {
                onSuccess(slices)
            }
        }
    }

    override suspend fun addSlices(slices: List<Slice>, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            val slicesEntities = slices.map { slice -> SliceEntity.fromSlice(slice) }
            sliceDao.insertAll(*slicesEntities.toTypedArray())
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    override suspend fun updateSlices(slices: List<Slice>, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            val slicesEntities = slices.map { slice -> SliceEntity.fromSlice(slice) }
            sliceDao.updateAll(*slicesEntities.toTypedArray())
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    override suspend fun deleteSlices(slices: List<Slice>, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            val slicesEntities = slices.map { slice -> SliceEntity.fromSlice(slice) }
            sliceDao.deleteSlices(*slicesEntities.toTypedArray())
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

}
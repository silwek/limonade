package com.silwek.limonade.datasources

import android.content.Context
import androidx.room.Room
import com.silwek.limonade.datasources.room.RoomAppDatabase
import com.silwek.limonade.datasources.room.entities.SliceConfigEntity
import com.silwek.limonade.models.SliceConfig
import com.silwek.limonade.view.slices.base.BaseSliceType
import com.silwek.limonade.view.slices.radiobuttons.RadioButtonsType
import com.silwek.limonade.view.slices.text.TextType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SliceConfigRepository {

    suspend fun loadAllConfig(onSuccess: (List<SliceConfig>) -> Unit)
    suspend fun addSliceConfig(sliceConfig: SliceConfig, onSuccess: (SliceConfig) -> Unit)
    suspend fun updateSliceConfig(sliceConfig: SliceConfig, onSuccess: () -> Unit)
    suspend fun deleteSliceConfig(sliceConfig: SliceConfig, onSuccess: () -> Unit)
}

class SliceConfigRoomRepository(context: Context) : SliceConfigRepository {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        RoomAppDatabase::class.java, "limonade-name"
    ).fallbackToDestructiveMigration().build()
    private val sliceConfigDao by lazy { db.sliceConfigDao() }

    override suspend fun loadAllConfig(onSuccess: (List<SliceConfig>) -> Unit) {
        withContext(Dispatchers.IO) {
            val slicesConfigEntities = sliceConfigDao.getAll()
            val slices =
                slicesConfigEntities.map { fromEntityToModel(it) }.toMutableList()

            withContext(Dispatchers.Main) {
                onSuccess(slices)
            }
        }
    }

    override suspend fun addSliceConfig(
        sliceConfig: SliceConfig,
        onSuccess: (SliceConfig) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val sliceConfigEntity = SliceConfigEntity.fromSlice(sliceConfig)
            sliceConfigDao.insert(sliceConfigEntity)
            withContext(Dispatchers.Main) {
                onSuccess(sliceConfig.apply { key = sliceConfigEntity.uid })
            }
        }
    }

    override suspend fun updateSliceConfig(sliceConfig: SliceConfig, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            sliceConfigDao.update(SliceConfigEntity.fromSlice(sliceConfig))
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    override suspend fun deleteSliceConfig(sliceConfig: SliceConfig, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            sliceConfigDao.delete(SliceConfigEntity.fromSlice(sliceConfig))
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    private fun getViewBuilderConfig(config: SliceConfig): BaseSliceType? {
        return when (config.viewtype) {
            RadioButtonsType.VIEW_TYPE -> RadioButtonsType(config)
            TextType.VIEW_TYPE -> TextType(config)
            else -> null
        }
    }

    private fun fromEntityToModel(sliceConfigEntity: SliceConfigEntity): SliceConfig {
        return object : SliceConfig(
            key = sliceConfigEntity.uid,
            viewtype = sliceConfigEntity.viewtype,
            label = sliceConfigEntity.label,
            config = sliceConfigEntity.config
        ) {
            override fun getViewTypeBuilder(): BaseSliceType {
                return getViewBuilderConfig(this) ?: TextType(this)
            }

        }
    }

}
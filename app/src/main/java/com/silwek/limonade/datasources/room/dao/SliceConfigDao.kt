package com.silwek.limonade.datasources.room.dao

import androidx.room.*
import com.silwek.limonade.datasources.room.entities.SliceConfigEntity

@Dao
interface SliceConfigDao {
    @Query("SELECT * FROM sliceconfigentity")
    fun getAll(): List<SliceConfigEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sliceConfig: SliceConfigEntity)

    @Update
    fun update(sliceConfig: SliceConfigEntity)

    @Delete
    fun delete(sliceConfig: SliceConfigEntity)
}
package com.silwek.limonade.datasources.room.dao

import androidx.room.*
import com.silwek.limonade.datasources.room.entities.SliceEntity

@Dao
interface SliceDao {
    @Query("SELECT * FROM sliceentity")
    fun getAll(): List<SliceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(slice: SliceEntity):Long

    @Update
    fun update(slice: SliceEntity)

    @Delete
    fun delete(slice: SliceEntity)

    @Delete
    fun deleteSlices(vararg slice: SliceEntity)
}
package com.silwek.limonade.datasources.room.dao

import androidx.room.*
import com.silwek.limonade.datasources.room.entities.SliceEntity
import java.time.LocalDate

@Dao
interface SliceDao {
    @Query("SELECT * FROM sliceentity")
    fun getAll(): List<SliceEntity>

    @Query("SELECT * FROM sliceentity WHERE day=:day")
    fun getAllForDay(day: LocalDate): List<SliceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(slice: SliceEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg slice: SliceEntity)

    @Update
    fun update(slice: SliceEntity)

    @Update
    fun updateAll(vararg slice: SliceEntity)

    @Delete
    fun delete(slice: SliceEntity)

    @Delete
    fun deleteSlices(vararg slice: SliceEntity)
}
package com.silwek.limonade.datasources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.silwek.limonade.datasources.room.dao.SliceConfigDao
import com.silwek.limonade.datasources.room.dao.SliceDao
import com.silwek.limonade.datasources.room.entities.SliceConfigEntity
import com.silwek.limonade.datasources.room.entities.SliceEntity
import com.silwek.limonade.secondToLocalDateTime
import com.silwek.limonade.toSecond
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Database(
    entities = [SliceEntity::class, SliceConfigEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun sliceDao(): SliceDao
    abstract fun sliceConfigDao(): SliceConfigDao
}

class Converters {
    @TypeConverter
    fun fromIsoDate(value: String?): LocalDate? {
        if (value == null) return null
        return LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
    }

    @TypeConverter
    fun dateToIsoDate(date: LocalDate?): String? {
        return date?.format(DateTimeFormatter.ISO_DATE)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.secondToLocalDateTime()
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toSecond()
    }
}

package com.silwek.limonade.datasources.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(indices = [Index(value = ["day", "key"], unique = true)])
data class SliceEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "day") var day: LocalDate,
    @ColumnInfo(name = "key") var key: String,
    @ColumnInfo(name = "value") var value: String
) {
}
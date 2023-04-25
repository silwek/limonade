package com.silwek.limonade.datasources.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.silwek.limonade.models.Slice
import java.time.LocalDate

@Entity(indices = [Index(value = ["day", "key"], unique = true)])
data class SliceEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    @ColumnInfo(name = "day") var day: LocalDate,
    @ColumnInfo(name = "key") var key: String,
    @ColumnInfo(name = "value") var value: String
) {

    companion object {
        fun fromSlice(slice: Slice): SliceEntity {
            return with(slice) {
                SliceEntity(
                    uid = id ?: 0,
                    day = LocalDate.from(day),
                    key = getSliceType(),
                    value = getSliceValue()
                )
            }
        }
    }
}
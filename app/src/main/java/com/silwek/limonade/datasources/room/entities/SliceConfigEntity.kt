package com.silwek.limonade.datasources.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.silwek.limonade.models.SliceConfig
import java.util.UUID

@Entity
data class SliceConfigEntity(
    @PrimaryKey var uid: String,
    @ColumnInfo(name = "viewtype") var viewtype: String,
    @ColumnInfo(name = "label") var label: String,
    @ColumnInfo(name = "config") var config: String
) {

    companion object {
        fun fromSlice(sliceConfig: SliceConfig): SliceConfigEntity {
            return with(sliceConfig) {
                SliceConfigEntity(
                    uid = key ?: UUID.randomUUID().toString(),
                    viewtype = viewtype,
                    label = label,
                    config = config
                )
            }
        }
    }
}
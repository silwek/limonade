package com.silwek.limonade.models

import com.silwek.limonade.datasources.room.entities.SliceEntity
import java.time.LocalDate

class StringSlice(id: Long?, day: LocalDate, type: String, var value: String) :
    Slice(id, day, type) {

    override fun getSliceValue(): String {
        return value
    }

    companion object {
        fun fromEntityToModel(sliceEntity: SliceEntity): Slice {
            return StringSlice(
                id = sliceEntity.uid,
                day = sliceEntity.day,
                type = sliceEntity.key,
                value = sliceEntity.value
            )
        }
    }
}
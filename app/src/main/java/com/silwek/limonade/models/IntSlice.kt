package com.silwek.limonade.models

import com.silwek.limonade.datasources.room.entities.SliceEntity
import java.time.LocalDate

class IntSlice(id: Long?, day: LocalDate, type: String, var value: Int?) : Slice(id, day, type) {

    override fun getSliceValue(): String {
        return value?.toString() ?: ""
    }

    companion object {
        fun fromEntityToModel(sliceEntity: SliceEntity): Slice {
            return IntSlice(
                id = sliceEntity.uid,
                day = sliceEntity.day,
                type = sliceEntity.key,
                value = sliceEntity.value.toInt()
            )
        }
    }
}
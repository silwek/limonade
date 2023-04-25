package com.silwek.limonade.models

import java.time.LocalDate

class IntSlice(id: Long?, day: LocalDate, type: String, var value: Int?) : Slice(id, day, type) {

    override fun getSliceValue(): String {
        return value?.toString() ?: ""
    }
}
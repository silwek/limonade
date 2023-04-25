package com.silwek.limonade.models

import java.time.LocalDate

class StringSlice(id: Long?, day: LocalDate, type: String, var value: String) : Slice(id, day, type) {

    override fun getSliceValue(): String {
        return value
    }
}
package com.silwek.limonade.models

import com.silwek.limonade.view.slices.SliceConfigHub
import com.silwek.limonade.view.slices.base.SliceConfig
import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class Slice(var id: Long?, var day: LocalDate, var type: String) {
    open fun getSliceType(): String {
        return type
    }

    open fun getSliceValue(): String {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Slice(id=$id, day=${day.format(DateTimeFormatter.ISO_DATE)}, type='$type', value='${getSliceValue()}')"
    }

    fun getConfig(): SliceConfig? {
        return SliceConfigHub.getConfig(type)
    }


}
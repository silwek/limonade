package com.silwek.limonade.models

import java.time.LocalDate

data class DaySlices(var date: LocalDate, var slices: List<Slice>, var slicesConfigs: List<SliceConfig?>)
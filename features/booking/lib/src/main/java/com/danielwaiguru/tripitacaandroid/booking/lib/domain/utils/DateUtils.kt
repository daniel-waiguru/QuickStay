package com.danielwaiguru.tripitacaandroid.booking.lib.domain.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    fun formatDate(timeInMillis: Long): String {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return try {
            format.format(timeInMillis)
        } catch (e: Exception) {
            e.printStackTrace()
            format.format(Date())
        }
    }

    val nextDayDate: Long
        get() {
            val calendar = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_WEEK, 1)
            }
            return calendar.timeInMillis
        }
}
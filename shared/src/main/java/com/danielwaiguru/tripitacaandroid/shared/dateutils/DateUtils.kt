package com.danielwaiguru.tripitacaandroid.shared.dateutils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    fun parseJsonDate(jsonDate: String, pattern: String = "yyyy-MM-dd"): String {
        val inFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val outFormat = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
        return try {
            val date = inFormat.parse(jsonDate)
            outFormat.format(date ?: Date())
        }catch (e: Exception) {
            e.printStackTrace()
            outFormat.format(Date())
        }
    }
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
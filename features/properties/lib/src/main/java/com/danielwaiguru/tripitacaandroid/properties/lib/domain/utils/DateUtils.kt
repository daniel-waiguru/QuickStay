package com.danielwaiguru.tripitacaandroid.properties.lib.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun parseJsonDate(jsonDate: String, pattern: String = "yyyy-MM-dd"): String {
        val inFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val outFormat = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
        return try {
            val date = inFormat.parse(jsonDate)
            outFormat.format(date ?: Date())
        } catch (e: Exception) {
            e.printStackTrace()
            outFormat.format(Date())
        }
    }
}
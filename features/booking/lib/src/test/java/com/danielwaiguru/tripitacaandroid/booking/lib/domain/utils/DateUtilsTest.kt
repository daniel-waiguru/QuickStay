package com.danielwaiguru.tripitacaandroid.booking.lib.domain.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class DateUtilsTest {

    @Test
    fun `formatDate renders the dd MM yyyy pattern`() {
        // 2021-01-15T00:00:00Z expressed in millis, formatted in the default locale/zone.
        val millis = 1_610_668_800_000L
        val expected = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(millis)

        assertThat(DateUtils.formatDate(millis)).isEqualTo(expected)
    }

    @Test
    fun `nextDayDate is in the future`() {
        assertThat(DateUtils.nextDayDate).isGreaterThan(System.currentTimeMillis())
    }
}
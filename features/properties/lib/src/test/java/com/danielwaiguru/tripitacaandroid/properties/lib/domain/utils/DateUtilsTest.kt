package com.danielwaiguru.tripitacaandroid.properties.lib.domain.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DateUtilsTest {

    @Test
    fun `parses an ISO date into the display format`() {
        assertThat(DateUtils.parseJsonDate("2009-11-21")).isEqualTo("21 Nov, 2009")
    }

    @Test
    fun `falls back to a non-empty string for an unparseable date`() {
        val result = DateUtils.parseJsonDate("not-a-date")

        assertThat(result).isNotEmpty()
    }
}
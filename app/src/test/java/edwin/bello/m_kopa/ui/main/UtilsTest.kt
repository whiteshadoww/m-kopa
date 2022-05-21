package edwin.bello.m_kopa.ui.main

import edwin.bello.m_kopa.data.ActiveUsagePeriod
import edwin.bello.m_kopa.ui.main.Utils
import org.junit.Test

import org.junit.Assert.*
import java.time.OffsetDateTime

class UtilsTest {
    @Test
    fun checkDateDiff() {

        val actUsagePeriod =
            ActiveUsagePeriod("", OffsetDateTime.now().withHour(23).withMinute(59).withSecond(59))
        val now = OffsetDateTime.now().withHour(5).withMinute(3).withSecond(5)

        val timeRemaining = Utils.timeRemaining(actUsagePeriod, now)

        assertEquals("0d 18h 56m 53s", timeRemaining)
    }
}
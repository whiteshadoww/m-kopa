package edwin.bello.m_kopa.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import edwin.bello.m_kopa.data.ActiveUsagePeriod
import java.time.Duration
import java.time.OffsetDateTime


object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun timeRemaining(aup: ActiveUsagePeriod?, now: OffsetDateTime): String {
        if (aup == null) return ""
        var duration = Duration.between(now, aup.usageExpiry)

        val days = duration.toDays()
        duration -= Duration.ofHours(days)
        val hours = duration.toHours()
        duration -= Duration.ofHours(hours)
        val minutes = duration.toMinutes()
        duration -= Duration.ofMinutes(minutes)
        val seconds = duration.seconds

        return "${days}d ${hours}h ${minutes}m ${seconds}s"
    }

}
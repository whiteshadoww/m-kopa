package edwin.bello.m_kopa.data.source

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import edwin.bello.m_kopa.data.ActiveUsagePeriod
import kotlinx.coroutines.delay
import timber.log.Timber
import java.time.OffsetDateTime

class ActiveUsagePeriodDataSourceImpl : ActiveUsagePeriodDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getLockingInfo(accountId: String): ActiveUsagePeriod {
        delay(500) // Simulate a network call
        return ActiveUsagePeriod(
            accountId,
            OffsetDateTime.now().withHour(23).withMinute(59).withSecond(59)
        )
    }
}
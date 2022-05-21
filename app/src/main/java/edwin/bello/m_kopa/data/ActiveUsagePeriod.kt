package edwin.bello.m_kopa.data

import java.time.OffsetDateTime

data class ActiveUsagePeriod(
    val accountId: String,
    val usageExpiry: OffsetDateTime,
)

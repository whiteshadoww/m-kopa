package edwin.bello.m_kopa.data.source;

import edwin.bello.m_kopa.data.ActiveUsagePeriod

interface ActiveUsagePeriodDataSource {
    suspend fun getLockingInfo(accountId: String): ActiveUsagePeriod
}
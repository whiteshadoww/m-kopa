package edwin.bello.m_kopa.data.source

import edwin.bello.m_kopa.data.ActiveUsagePeriod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UsagePeriodRepositoryImpl(
    private val remoteActiveUsagePeriodDataSource: ActiveUsagePeriodDataSource,
    private val ioDispatcher: CoroutineDispatcher
) :
    UsagePeriodRepository {

    override suspend fun getLockingInfo(accountId: String): ActiveUsagePeriod =
        withContext(ioDispatcher) {
            remoteActiveUsagePeriodDataSource.getLockingInfo(accountId)
        }
}
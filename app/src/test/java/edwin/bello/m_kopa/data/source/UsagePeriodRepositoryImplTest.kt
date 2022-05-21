package edwin.bello.m_kopa.data.source

import edwin.bello.m_kopa.MainCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.OffsetDateTime

@ExperimentalCoroutinesApi
class UsagePeriodRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var remoteActiveUsagePeriodDataSource: ActiveUsagePeriodDataSource
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    // Class under test
    private lateinit var tasksRepository: UsagePeriodRepositoryImpl


    @ExperimentalCoroutinesApi
    @Before
    fun createRepository() {
        remoteActiveUsagePeriodDataSource = ActiveUsagePeriodDataSourceImpl()
        // Get a reference to the class under test
        tasksRepository = UsagePeriodRepositoryImpl(remoteActiveUsagePeriodDataSource, ioDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getLockingInfo_CorrectTime() = mainCoroutineRule.runBlockingTest {
        val accountId = ""
        val activeUsagePeriod = remoteActiveUsagePeriodDataSource.getLockingInfo(accountId)
        val fakedTime = OffsetDateTime.now().withHour(23).withMinute(59).withSecond(59)
        assertEquals(
            true,
            activeUsagePeriod.usageExpiry.toEpochSecond() == fakedTime.toEpochSecond()
        )
    }
}
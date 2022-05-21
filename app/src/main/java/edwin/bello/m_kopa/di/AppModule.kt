package edwin.bello.m_kopa.di;

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edwin.bello.m_kopa.data.source.ActiveUsagePeriodDataSource
import edwin.bello.m_kopa.data.source.ActiveUsagePeriodDataSourceImpl
import edwin.bello.m_kopa.data.source.UsagePeriodRepository
import edwin.bello.m_kopa.data.source.UsagePeriodRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Module to tell Hilt how to provide instances of types that cannot be constructor-injected.
 *
 * As these types are scoped to the application lifecycle using @Singleton, they're installed
 * in Hilt's ApplicationComponent.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class NetworkUsagePeriodDataSource


    @Singleton
    @NetworkUsagePeriodDataSource
    @Provides
    fun provideRemoteUsagePeriodDataSource(): ActiveUsagePeriodDataSource {
        return ActiveUsagePeriodDataSourceImpl()
    }


    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

/**
 * The binding for TasksRepository is on its own module so that we can replace it easily in tests.
 */
@Module
@InstallIn(SingletonComponent::class)
object UsagePeriodRepositoryModule {

    @Singleton
    @Provides
    fun provideUsagePeriodRepository(
        @AppModule.NetworkUsagePeriodDataSource remoteActiveUsagePeriodDataSource: ActiveUsagePeriodDataSource,
        ioDispatcher: CoroutineDispatcher
    ): UsagePeriodRepository {
        return UsagePeriodRepositoryImpl(
            remoteActiveUsagePeriodDataSource, ioDispatcher
        )
    }
}
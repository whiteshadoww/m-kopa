package edwin.bello.m_kopa.ui.main

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import edwin.bello.m_kopa.data.ActiveUsagePeriod
import edwin.bello.m_kopa.data.source.UsagePeriodRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usagePeriodRepository: UsagePeriodRepository) :
    ViewModel() {

    private val _accountId = MutableLiveData<String>("dddd")
    private val _activeUsagePeriod = MutableLiveData<ActiveUsagePeriod>()
    val activeUsagePeriod: LiveData<ActiveUsagePeriod?> = _activeUsagePeriod

    fun getLocking() = viewModelScope.launch {
        val accountId = _accountId.value ?: return@launch
        val activeUsagePeriod = usagePeriodRepository.getLockingInfo(accountId)
        _activeUsagePeriod.postValue(activeUsagePeriod)
    }


}
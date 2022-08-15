package com.polinasmogi.dailyactivity.ui.dailyactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polinasmogi.dailyactivity.utils.toActivityWrapperList
import com.polinasmogi.dailyactivity.models.WeeklyDataModel
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper
import com.polinasmogi.dailyactivity.repository.DailyItemsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.coroutines.CoroutineContext

class DailyActivityViewModel(
    private val repository: DailyItemsRepository
): ViewModel(), CoroutineScope {

    private val _weeklyActivity = MutableLiveData<List<DailyActivityWrapper>>()
    val weeklyActivity: LiveData<List<DailyActivityWrapper>> = _weeklyActivity

    private val _loadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean> = _loadingData

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            Log.d(TAG, "Error on coroutine", throwable)
        }

    init {
        launch {
            getWeeklyActivity()
        }
    }

    fun getWeeklyActivity() {
        //TODO create a loading animation
//        _loadingData.value = true

        val lastRequestTime = repository.getLastRequestTime()
        val recentTime = Calendar.getInstance().timeInMillis
        val dif = recentTime - lastRequestTime

        //Maybe I should put that time logic into a interactor
        if (dif > TIME_TO_UPDATE) {
            Log.d(TAG, "Data is from API, last request = $lastRequestTime, time = $recentTime")
            repository.getWeeklyDataFromApi().enqueue(object : Callback<WeeklyDataModel> {
                override fun onResponse(
                    call: Call<WeeklyDataModel>,
                    response: Response<WeeklyDataModel>
                ) {
//                _loadingData.value = false
                    if (response.isSuccessful) {
                        viewModelScope.launch(Dispatchers.IO) {
                            response.body()?.let { repository.safeWeeklyDataInDatabase(it) }
                        }
                        _weeklyActivity.value = response.body()?.toActivityWrapperList()
                    }
                }

                override fun onFailure(call: Call<WeeklyDataModel>, t: Throwable) {
//                _loadingData.value = false
                }
            })
        } else {
            Log.d(TAG, "Data is from Database, last request = $lastRequestTime, time = $recentTime")
            viewModelScope.launch {
                repository.getWeeklyDataFromDatabase().collect { weeklyData ->
                    _weeklyActivity.value = weeklyData.toActivityWrapperList()
                }
            }
        }
    }

    companion object {
        private const val TAG = "DailyActivityViewModel"
        private const val TIME_TO_UPDATE = 43200000 // 12 hours
    }

}
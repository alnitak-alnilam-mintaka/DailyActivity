package com.polinasmogi.dailyactivity.ui.timeline

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinasmogi.dailyactivity.interactor.ActivityInteractor
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimeLineViewModel(
    private val interactor: ActivityInteractor
): ViewModel(), CoroutineScope {

    private val _weeklyActivity = MutableLiveData<List<DailyActivityWrapper>>()
    val weeklyActivity: LiveData<List<DailyActivityWrapper>> = _weeklyActivity

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            Log.d(TAG, "Error on coroutine", throwable)
        }

    //TODO get saved information from the database using the interactor

    companion object {
        private const val TAG = "TimeLineViewModel"
    }

}
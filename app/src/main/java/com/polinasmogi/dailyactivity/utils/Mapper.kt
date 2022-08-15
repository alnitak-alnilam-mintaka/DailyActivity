package com.polinasmogi.dailyactivity.utils

import com.polinasmogi.dailyactivity.database.ActivityEntity
import com.polinasmogi.dailyactivity.models.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max

fun WeeklyDataItem.toActivityEntity() = map(this)
fun List<WeeklyDataItem>.toActivityEntity() = map { it.toActivityEntity() }

private fun map(activity: WeeklyDataItem) = ActivityEntity (
    dailyGoal = activity.dailyItem.dailyGoal,
    dailyActivity = activity.dailyItem.dailyActivity,
    dailyDistanceMeters = activity.dailyData.dailyDistanceMeters,
    dailyKcal = activity.dailyData.dailyKcal
)

fun ActivityEntity.toDataItem() = map(this)
fun List<ActivityEntity>.toDataItem() = map { it.toDataItem() }

private fun map(activityEntity: ActivityEntity) = WeeklyDataItem (
    dailyItem = DailyItem(
        dailyGoal = activityEntity.dailyGoal,
        dailyActivity = activityEntity.dailyActivity
    ),
    dailyData = DailyData(
        dailyDistanceMeters = activityEntity.dailyDistanceMeters,
        dailyKcal = activityEntity.dailyKcal
    )
)

fun WeeklyDataModel.toActivityWrapperList() = map(this)

private fun map(model: WeeklyDataModel): ArrayList<DailyActivityWrapper> {
    val items = arrayListOf<DailyActivityWrapper>()
    model.apply {
        val maxValue = max(
            weeklyData.takeLast(7).maxOf { it.dailyItem.dailyGoal },
            weeklyData.takeLast(7).maxOf { it.dailyItem.dailyActivity }
        )
        weeklyData.takeLast(7).mapIndexed { index, item ->
            item.let {
                val date = getDate(index)
                items.add(
                    DailyActivityWrapper(
                        dailyGoal = "/${it.dailyItem.dailyGoal}",
                        dailyGoalInPercents = (100.0 * it.dailyItem.dailyGoal / maxValue).toInt(),
                        dailyGoalInPercentsForTimeline = (100.0 * it.dailyItem.dailyActivity / it.dailyItem.dailyGoal).toInt(),
                        dailyActivity = it.dailyItem.dailyActivity,
                        dailyActivityInPercents = (100.0 * it.dailyItem.dailyActivity / maxValue).toInt(),
                        goalReached = it.dailyItem.dailyActivity >= it.dailyItem.dailyGoal,
                        dailyKcal = "${it.dailyData.dailyKcal} kcal",
                        dailyDistanceMeters = "${it.dailyData.dailyDistanceMeters} m",
                        dayOfMonth = SimpleDateFormat("dd", Locale.ENGLISH).format(date.time),
                        month = SimpleDateFormat("MMMM", Locale.ENGLISH).format(date.time),
                        dayOfWeek = SimpleDateFormat("EE", Locale.ENGLISH).format(date.time),
                        isToday = index == 6
                    )
                )
            }
        }
    }
    return items
}

fun List<ActivityEntity>.toActivityWrapperList() = transformToList(this)

private fun transformToList(list: List<ActivityEntity>): ArrayList<DailyActivityWrapper> {
    val items = arrayListOf<DailyActivityWrapper>()
    val lastWeek = list.takeLast(7)
    val maxValue = max(
        lastWeek.maxOf { it.dailyGoal },
        lastWeek.maxOf { it.dailyActivity }
    )
    lastWeek.mapIndexed { index, activityEntity ->
        val date = getDate(index)
        items.add(
            DailyActivityWrapper(
                dailyGoal = "/${activityEntity.dailyGoal}",
                dailyGoalInPercents = (100.0 * activityEntity.dailyGoal / maxValue).toInt(),
                dailyGoalInPercentsForTimeline = (100.0 * activityEntity.dailyActivity / activityEntity.dailyGoal).toInt(),
                dailyActivity = activityEntity.dailyActivity,
                dailyActivityInPercents = (100.0 * activityEntity.dailyActivity / maxValue).toInt(),
                goalReached = activityEntity.dailyActivity >= activityEntity.dailyGoal,
                dailyKcal = "${activityEntity.dailyKcal} kcal",
                dailyDistanceMeters = "${activityEntity.dailyDistanceMeters} m",
                dayOfMonth = SimpleDateFormat("dd", Locale.ENGLISH).format(date.time),
                month = SimpleDateFormat("MMMM", Locale.ENGLISH).format(date.time),
                dayOfWeek = SimpleDateFormat("EE", Locale.ENGLISH).format(date.time),
                isToday = index == 6
            )
        )
    }
    return items
}

private fun getDate(position: Int): Calendar {
    val calendar = Calendar.getInstance()
    when (position) {
        0 -> calendar.add(Calendar.DATE, -6)
        1 -> calendar.add(Calendar.DATE, -5)
        2 -> calendar.add(Calendar.DATE, -4)
        3 -> calendar.add(Calendar.DATE, -3)
        4 -> calendar.add(Calendar.DATE, -2)
        5 -> calendar.add(Calendar.DATE, -1)
        else -> {}
    }
    return calendar
}




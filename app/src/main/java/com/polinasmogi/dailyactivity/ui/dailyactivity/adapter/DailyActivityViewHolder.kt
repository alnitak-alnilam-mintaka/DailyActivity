package com.polinasmogi.dailyactivity.ui.dailyactivity.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.polinasmogi.dailyactivity.databinding.ItemDailyProgressBinding
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper

class DailyActivityViewHolder(private val binding: ItemDailyProgressBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dailyActivity: DailyActivityWrapper) {
        binding.apply {
            activityProgress.progress = dailyActivity.dailyActivityInPercents
            goalProgress.progress = dailyActivity.dailyGoalInPercents
            dayOfWeek.text = dailyActivity.dayOfWeek
            todayIndicator.isVisible = dailyActivity.isToday
        }
    }

}
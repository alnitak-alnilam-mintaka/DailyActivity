package com.polinasmogi.dailyactivity.ui.timeline.adapter

import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.polinasmogi.dailyactivity.R
import com.polinasmogi.dailyactivity.databinding.ItemTimelineBinding
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper

class TimelineViewHolder(private val binding: ItemTimelineBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(activity: DailyActivityWrapper) {
        binding.apply {
            dayTextview.text = activity.dayOfMonth
            dayOfWeekTextview.text = activity.dayOfWeek
            progressBar.progress = activity.dailyGoalInPercentsForTimeline
            stepsActivity.text = activity.dailyActivity.toString()
            stepsGoal.text = activity.dailyGoal
            kcalTextview.text = activity.dailyKcal
            kmTextview.text = activity.dailyDistanceMeters

            todayIndicator.isVisible = activity.isToday

            //colors
            val contentColorRes = if (activity.goalReached) R.color.green else R.color.blue
            val color = ResourcesCompat.getColor(itemView.resources, contentColorRes, itemView.context.theme)
            progressBar.setIndicatorColor(color)
            stepsActivity.setTextColor(color)

            val indicatorDrawableRes =
                if (activity.goalReached) R.drawable.green_indicator_drawable
                else R.drawable.gray_indicator_drawable
            val drawable = ResourcesCompat.getDrawable(itemView.resources, indicatorDrawableRes, itemView.context.theme)
            kcalIndicator.setImageDrawable(drawable)
            kmIndicator.setImageDrawable(drawable)

        }
    }

}
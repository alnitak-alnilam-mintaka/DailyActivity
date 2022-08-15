package com.polinasmogi.dailyactivity.ui.timeline.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polinasmogi.dailyactivity.databinding.ItemTimelineBinding
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper
import java.util.ArrayList

class TimelineAdapter: RecyclerView.Adapter<TimelineViewHolder>() {

    private val items = arrayListOf<DailyActivityWrapper>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding = ItemTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun build(list: ArrayList<DailyActivityWrapper>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

}
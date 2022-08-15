package com.polinasmogi.dailyactivity.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.polinasmogi.dailyactivity.databinding.FragmentTimelineBinding
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper
import com.polinasmogi.dailyactivity.ui.timeline.adapter.TimelineAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimeLineFragment: Fragment() {

    private val viewModel: TimeLineViewModel by viewModel()

    private var _binding: FragmentTimelineBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = this.arguments?.getParcelableArrayList<DailyActivityWrapper>("ITEMS")
        items?.let {
            adapter = TimelineAdapter()
            adapter.build(items)
            binding.progressRecycler.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.polinasmogi.dailyactivity.ui.dailyactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.polinasmogi.dailyactivity.R
import com.polinasmogi.dailyactivity.databinding.FragmentDailyActivityBinding
import com.polinasmogi.dailyactivity.ui.dailyactivity.adapter.DailyActivityAdapter
import com.polinasmogi.dailyactivity.models.DailyActivityWrapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyActivityFragment: Fragment() {

    private val viewModel: DailyActivityViewModel by viewModel()

    private var _binding: FragmentDailyActivityBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DailyActivityAdapter
    private val items = arrayListOf<DailyActivityWrapper>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weeklyActivity.observe(viewLifecycleOwner) {
            items.clear()
            items.addAll(it)
            adapter = DailyActivityAdapter()
            adapter.build(it)
            binding.progressRecycler.adapter = adapter
        }

        viewModel.loadingData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
            binding.progressRecycler.isVisible = !it
        }

        binding.timelineButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("ITEMS", items)
            findNavController().navigate(R.id.action_DailyActivityFragment_to_FirstFragment, bundle)
        }

        binding.weeklyProgressText.setOnClickListener {
            viewModel.getWeeklyActivity()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
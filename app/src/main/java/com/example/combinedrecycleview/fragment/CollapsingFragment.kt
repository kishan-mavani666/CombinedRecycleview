package com.example.combinedrecycleview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.combinedrecycleview.R
import com.example.combinedrecycleview.adapter.NearJobAdapter
import com.example.combinedrecycleview.viewmodel.HomeViewModel


class CollapsingFragment : Fragment() {

    private var viewModel: HomeViewModel = HomeViewModel()
    private var nearestJobsAdapter: NearJobAdapter = NearJobAdapter()
    private var isScrolling: Boolean = false
    private var totalPageCount: Int = 0
    private lateinit var recycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collapsing, container, false)

        initView(view)
        initObserver()
        initListener()

        return view
    }

    private fun initView(view: View) {
        recycleView = view.findViewById<RecyclerView>(R.id.rv_near_jobs)
        recycleView.adapter = nearestJobsAdapter
        viewModel.onGetCombinedJobs()
    }

    private fun initObserver() {
        viewModel.mutCombinedJobsResponse.observe(requireActivity()) {
            if (it != null) {
                when (it.responseCode) {
                    "1111" -> {
                        val jobsList = it.content.jobs
                        if (!jobsList.isNullOrEmpty()) {
                            nearestJobsAdapter.setData(jobsList, jobsList)
                        }
                    }
                    "0000" -> {
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun initListener() {
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager.itemCount
                val currentItemsCount = layoutManager.childCount
                val scrollOutItems = layoutManager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItemsCount + scrollOutItems == totalItemCount)) {
                    isScrolling = false
                    if (totalPageCount != viewModel.pageNumber) {
                        viewModel.pageNumber = (viewModel.pageNumber + 1)
                        viewModel.onGetCombinedJobs()
                    }
                }
            }
        })

    }
}
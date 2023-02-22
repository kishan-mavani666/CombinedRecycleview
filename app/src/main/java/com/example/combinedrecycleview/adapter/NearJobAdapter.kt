package com.example.combinedrecycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.combinedrecycleview.R
import com.example.combinedrecycleview.databinding.ItemSuggestedJobsBinding
import com.example.combinedrecycleview.model.Job

class NearJobAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_NEAR_JOB = 0
    private val VIEW_SUGGESTED_JOB = 1

    private var arrNearJobs: List<Job> = ArrayList()
    private var arrSuggestedJobs: List<Job> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
        return if (i == VIEW_NEAR_JOB) {
            val binding: ItemSuggestedJobsBinding =
                DataBindingUtil.inflate(v, R.layout.item_suggested_jobs, viewGroup, false)
            NearJobViewHolder(binding)
        } else {
            val binding: ItemSuggestedJobsBinding =
                DataBindingUtil.inflate(v, R.layout.item_suggested_jobs, viewGroup, false)
            SuggestedJobViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = holder.itemView.context
        if (holder is NearJobViewHolder) {
            if (position == arrNearJobs.size) {
                holder.binding.rlRecycleview.visibility = View.VISIBLE
            } else {
                holder.binding.rlRecycleview.visibility = View.GONE
            }
            val job = arrNearJobs[position]
            holder.binding.apply {
                salary = "${job.salary}/m"
                tvTitle.text = "${job.title}"

                Glide.with(context).load(job.jobIconURL)
                    .placeholder(R.drawable.img_painter)
                    .error(R.drawable.img_painter)
                    .into(ivProfile)
                executePendingBindings()
            }
        }

        if (holder is SuggestedJobViewHolder) {
            if ((position - arrNearJobs.size) == 0) {
                holder.binding.rlRecycleview.visibility = View.VISIBLE
            } else {
                holder.binding.rlRecycleview.visibility = View.GONE
            }
            val job = arrSuggestedJobs[position - arrNearJobs.size]
            holder.binding.apply {
                salary = "${job.salary}/m"
                Glide.with(context).load(job.jobIconURL)
                    .placeholder(R.drawable.img_painter)
                    .error(R.drawable.img_painter)
                    .into(ivProfile)
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position < arrNearJobs.size) {
            return VIEW_NEAR_JOB
        }
        return if (position - arrNearJobs.size < arrSuggestedJobs.size) {
            VIEW_SUGGESTED_JOB
        } else -1
    }

    override fun getItemCount(): Int = (arrNearJobs.size + arrSuggestedJobs.size)

    fun setData(nearJobList: List<Job>, suggestedJobList: List<Job>) {
        arrNearJobs = nearJobList
        arrSuggestedJobs = suggestedJobList
        notifyDataSetChanged()
    }

    inner class NearJobViewHolder(var binding: ItemSuggestedJobsBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class SuggestedJobViewHolder(var binding: ItemSuggestedJobsBinding) :
        RecyclerView.ViewHolder(binding.root)

}
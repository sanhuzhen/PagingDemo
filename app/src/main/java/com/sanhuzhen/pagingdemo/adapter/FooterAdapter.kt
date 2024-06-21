package com.sanhuzhen.pagingdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.pagingdemo.R

class FooterAdapter(val retry: () -> Unit): LoadStateAdapter<FooterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val pg: ProgressBar = itemView.findViewById(R.id.progress_bar1)
        val bt_retry: Button = itemView.findViewById(R.id.retry_button)
    }
    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.pg.isVisible = loadState is LoadState.Loading
        holder.bt_retry.isVisible = loadState is LoadState.Error
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
        return ViewHolder(view).apply {
            bt_retry.setOnClickListener {
                retry.invoke()
            }
        }
    }

}
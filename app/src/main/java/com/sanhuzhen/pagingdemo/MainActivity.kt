package com.sanhuzhen.pagingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.sanhuzhen.pagingdemo.adapter.FooterAdapter
import com.sanhuzhen.pagingdemo.adapter.RvAdapter
import com.sanhuzhen.pagingdemo.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val mAdapter = RvAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view)
        val pg = findViewById<ProgressBar>(R.id.progress_bar)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter.withLoadStateFooter(
            FooterAdapter { mAdapter.retry() }
        )
        lifecycleScope.launch {
            mViewModel.getPagingData().collect {
                mAdapter.submitData(it)
            }
        }
        mAdapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.Loading -> {
                    pg.visibility = View.VISIBLE
                    rv.visibility = View.INVISIBLE
                }
                is LoadState.NotLoading -> {
                    pg.visibility = View.GONE
                    rv.visibility = View.VISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    pg.visibility = View.GONE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
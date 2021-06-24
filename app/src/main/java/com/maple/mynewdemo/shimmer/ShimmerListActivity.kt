package com.maple.mynewdemo.shimmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maple.mynewdemo.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class ShimmerListActivity : AppCompatActivity() {
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private val adapter: ShimmerAdapter by lazy { ShimmerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shimmer_list)
        refreshLayout = this.findViewById(R.id.refreshLayout)
        recyclerView = this.findViewById(R.id.recyclerView)

        refreshLayout.let {
            it.setEnableRefresh(true)//是否启用下拉刷新功能
            it.setEnableLoadMore(true)//是否启用上拉加载功能
            it.setOnRefreshListener { ref ->
                onRefreshData()
            }
            it.setOnLoadMoreListener { ref ->
                onLoadMoreData()
            }
        }

        val mlist: MutableList<String> = mutableListOf()
        for (index in 1..10) {
            mlist.add(index.toString())
        }
        adapter.updateList(mlist)
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(this@ShimmerListActivity)
            it.adapter = adapter
        }
    }

    private fun onLoadMoreData() {
        val mlist: MutableList<String> = mutableListOf()
        for (index in 1..10) {
            mlist.add(index.toString())
        }
        adapter.updateList(mlist)
        this.finishLoadMore()
    }

    private fun onRefreshData() {
        val mlist: MutableList<String> = mutableListOf()
        for (index in 1..10) {
            mlist.add(index.toString())
        }
        adapter.clearList()
        adapter.updateList(mlist)
        this.finishRefresh()
    }

    //结束下拉刷新
    protected fun finishRefresh() {
        refreshLayout.let {
            if (it.isRefreshing) it.finishRefresh(300)
        }
    }

    //结束加载更多
    protected fun finishLoadMore() {
        refreshLayout.let {
            if (it.isLoading) it.finishLoadMore(300)
        }
    }
}
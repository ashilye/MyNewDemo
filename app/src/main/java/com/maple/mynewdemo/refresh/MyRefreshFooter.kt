package com.maple.mynewdemo.refresh

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle

class MyRefreshFooter : LinearLayout, RefreshFooter {

    private lateinit var tvFooter: TextView


    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        initView(
            context,
            attributeSet,
            defStyleAttr
        )
    }

    private fun initView(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) {
        setGravity(Gravity.CENTER)
        this.tvFooter = TextView(context)
        addView(this.tvFooter, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.None,
            RefreshState.PullDownToRefresh -> {
                this.tvFooter.text = "下拉开始刷新"
            }
            RefreshState.Refreshing -> {
                this.tvFooter.text = "正在刷新"
            }
            RefreshState.ReleaseToRefresh -> {
                this.tvFooter.text = "释放立即刷新"
            }
            else -> this.tvFooter.text = "刷新啊啊啊"
        }
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun setPrimaryColors(vararg colors: Int) {
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        this.tvFooter.text = if (success) {
            "刷新完成"
        } else {
            "刷新失败"
        }
        return 500 //延迟500毫秒之后再弹回
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun setNoMoreData(noMoreData: Boolean): Boolean {
        return false
    }
}
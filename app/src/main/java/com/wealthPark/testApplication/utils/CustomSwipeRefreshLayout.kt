package com.wealthPark.testApplication.utils

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wealthPark.testApplication.R

class CustomSwipeRefreshLayout : SwipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener {
    private var listener: Event? = null


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        setOnRefreshListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setColorSchemeColors(context.getColor(R.color.colorAccent))
            this.setProgressBackgroundColorSchemeColor(context.getColor(R.color.teal_700))
        }

    }

    fun setEventListener(event: Event) {
        this.listener = event
    }

    override fun onRefresh() {
        listener?.onRefresh(this)
        this.isRefreshing = false
    }

    interface Event {
        fun onRefresh(view: CustomSwipeRefreshLayout)
    }

}
package com.tanxf.hiltmvvm.ui.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dylanc.loadinghelper.LoadingHelper
import com.tanxf.hiltmvvm.R

class LoadingAdapter() : LoadingHelper.Adapter<LoadingHelper.ViewHolder>() {

    private var mHeight = ViewGroup.LayoutParams.MATCH_PARENT

    constructor(height: Int) : this() {
        mHeight = height
    }

    override fun onBindViewHolder(holder: LoadingHelper.ViewHolder) {
        val layoutParams = holder.rootView.layoutParams
        layoutParams.height = mHeight
        holder.rootView.layoutParams = layoutParams
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LoadingHelper.ViewHolder {
        return LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_loading, parent, false))
    }
}
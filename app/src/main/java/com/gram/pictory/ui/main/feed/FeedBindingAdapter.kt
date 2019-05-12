package com.gram.pictory.ui.main.feed

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.gram.pictory.adapter.FeedAdapter
import com.gram.pictory.model.FeedModel

@BindingAdapter("feedData")
fun RecyclerView.setFeedData(data: LiveData<ArrayList<FeedModel>>) {
    val feedAdapter: FeedAdapter = adapter as FeedAdapter
    data.value.let { feedAdapter.item = it!! }
}
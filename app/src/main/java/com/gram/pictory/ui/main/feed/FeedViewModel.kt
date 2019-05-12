package com.gram.pictory.ui.main.feed

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.gram.pictory.connect.Connecter.api
import com.gram.pictory.model.FeedModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedViewModel(val app: Application) : AndroidViewModel(app) {
    val model = MutableLiveData<ArrayList<FeedModel>>()
    val feedLiveData = model.value
    fun getFeed() {
        api.getFeed().enqueue(object : Callback<ArrayList<FeedModel>> {
            override fun onResponse(call: Call<ArrayList<FeedModel>>, response: Response<ArrayList<FeedModel>>) {
                model.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<FeedModel>>, t: Throwable) {

            }
        })
    }
}
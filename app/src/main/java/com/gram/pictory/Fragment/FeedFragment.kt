package com.gram.pictory.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gram.pictory.Activity.ReplyActivity
import com.gram.pictory.Connect.Connecter.api
import com.gram.pictory.Model.FeedModel
import com.gram.pictory.R
import com.gram.pictory.Util.FeedClickCallback
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment: Fragment(), FeedClickCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        //feed_recyclerview.layoutManager = LinearLayoutManager(activity)
        //feed_recyclerview.adapter = FeedAdapter(setAdapterData(), this)
        return view
    }

    fun setAdapterData(): ArrayList<FeedModel> {
        var feedData = ArrayList<FeedModel>()
        api.getFeed().enqueue(object: Callback<ArrayList<FeedModel>>{
            override fun onResponse(call: Call<ArrayList<FeedModel>>?, response: Response<ArrayList<FeedModel>>?) {
                feedData = response!!.body()
            }

            override fun onFailure(call: Call<ArrayList<FeedModel>>?, t: Throwable?) {
                toast("피드를 가져올 수 없습니다.").show()
            }
        })
        return feedData
    }

    override fun intentToReply(postCode: Int, imageUrl: String, username: String, postText: String)
            = startActivity<ReplyActivity>("postCode" to postCode, "imageUrl" to imageUrl, "username" to username, "postText" to String)

}
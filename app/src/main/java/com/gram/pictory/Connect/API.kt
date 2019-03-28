package com.gram.pictory.Connect

import com.gram.pictory.Model.FeedModel
import com.gram.pictory.Model.FollowerModel
import com.gram.pictory.Model.LoginModel
import com.gram.pictory.Model.ReplyListModel
import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("/signup/")
    @Headers("Content-Type:application/json")
    fun signUp(@Body body: Any?): Call<Unit>

    @POST("/login/")
    @Headers("Content-Type:application/json")
    fun login(@Body body: Any?): Call<LoginModel>

    @POST("/post/")
    @Headers("Content-Type:application/json")
    fun post(@Body body: Any?): Call<Unit>

    @GET("/{followPath}/")
    @Headers("Content-Type:application/json")
    fun getFollower(@Path("followPath") followPath: String): Call<ArrayList<FollowerModel>>

    @PATCH("/{followPath}/cancel/")
    @Headers("Content-Type:application/json")
    fun cancelFollow(@Path("followPath") followPath: String, @Body body: Any?): Call<Unit>

    @POST("/{followPath}/follow/")
    @Headers("Content-Type:application/json")
    fun startFollow(@Path("followPath") followPath: String, @Body body: Any?): Call<Unit>

    @GET("/feed/")
    @Headers("Content-Type:application/json")
    fun getFeed():Call<ArrayList<FeedModel>>

    @GET("/{postCode}/reply/")
    @Headers("Content-Type:application/json")
    fun getReply(@Path("postCode") postCode: Int): Call<ArrayList<ReplyListModel>>

    @POST("/{postCode}/reply/")
    @Headers("Content-Type:application/json")
    fun postReply(@Path("postCode") postCode: Int, @Body body: Any?): Call<Unit>

    @PATCH("/postEdit/")
    @Headers("Content-Type:application/json")
    fun signEdit(@Body body: Any?): Call<Unit>


}
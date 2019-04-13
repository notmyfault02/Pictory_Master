package com.gram.pictory.Connect

import com.google.gson.JsonObject
import com.gram.pictory.Model.FeedModel
import com.gram.pictory.Model.FollowerModel
import com.gram.pictory.Model.LoginModel
import com.gram.pictory.Model.ReplyListModel
import retrofit2.Call
import retrofit2.http.*

interface API {

    //회원가입
    @POST("api/auth/register")
    @Headers("Content-Type:application/json")
    fun signUp(@Body body: JsonObject): Call<Unit>

    //로그인
    @POST("api/auth/login")
    @Headers("Content-Type:application/json")
    fun login(@Body body: JsonObject): Call<LoginModel>

    //글 게시
    @POST("/post/")
    @Headers("Content-Type:application/json")
    fun post(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>

    //팔로워 불러오기
    @GET("/{followPath}/")
    @Headers("Content-Type:application/json")
    fun getFollower(@Path("followPath") followPath: String): Call<ArrayList<FollowerModel>>

    //팔로우 취소하기
    @PATCH("/{followPath}/cancel/")
    @Headers("Content-Type:application/json")
    fun cancelFollow(@Path("followPath") followPath: String, @Body body: Any?): Call<Unit>

    //팔로우 하기
    @POST("/{followPath}/follow/")
    @Headers("Content-Type:application/json")
    fun startFollow(@Path("followPath") followPath: String, @Body body: Any?): Call<Unit>

    //피드 불러오기
    @GET("/feed/")
    @Headers("Content-Type:application/json")
    fun getFeed():Call<ArrayList<FeedModel>>

    //댓글 불러오기
    @GET("/{postCode}/reply/")
    @Headers("Content-Type:application/json")
    fun getReply(@Path("postCode") postCode: Int): Call<ArrayList<ReplyListModel>>

    //댓글 작성
    @POST("/{postCode}/reply/")
    @Headers("Content-Type:application/json")
    fun postReply(@Header("Authorization") token: String, @Path("postCode") postCode: Int, @Body body: Any?): Call<Unit>

    //프로필 수정
    @PATCH("/postEdit/")
    @Headers("Content-Type:application/json")
    fun signEdit(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>


}
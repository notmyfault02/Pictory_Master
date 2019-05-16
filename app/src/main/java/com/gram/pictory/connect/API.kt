package com.gram.pictory.connect

import com.google.gson.JsonObject
import com.gram.pictory.model.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface API {

    //회원가입
    //완료
    @Multipart
    @POST("auth/register")
    fun signUp(
        @Part image: MultipartBody.Part,
        @Part("username") userName: RequestBody,
        @Part("id") id: RequestBody,
        @Part("pw") pw: RequestBody,
        @Part("birth") birth: RequestBody,
        @Part("active") active: RequestBody
    ): Call<Unit>

    //로그인
    //완료
    @POST("auth/login")
    @Headers("Content-Type:application/json")
    fun login(@Body body: JsonObject): Call<LoginModel>

    //글 게시
    //완료
    @Multipart
    @POST("post/")
    fun post(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Part ("text") text: RequestBody
    ): Call<Unit>

    //마이페이지 유저정보 불러오기
    @GET("myPage/")
    fun getUserInfo(@Header("x-access-token") token: String): Single<UserModel>

    //팔로워 불러오기

    @GET("{followPath}/")
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
    //완료
    @Multipart
    @PATCH("/postEdit/")
    fun profileEdit(
        @Header("Authorization") token: String,
        @Part("username") userName: RequestBody,
        @Part("id") id: RequestBody,
        @Part("birth") birth: RequestBody
    ): Single<Unit>

}
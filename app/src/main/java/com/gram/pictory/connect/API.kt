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
    @POST("post/postCreate")
    fun post(
        @Header("x-access-token") token: String,
        @Part image: MultipartBody.Part,
        @Part ("text") text: RequestBody
    ): Call<Unit>

    //마이페이지 유저정보 불러오기
    @GET("Mypage/")
    fun getUserInfo(@Header("x-access-token") token: String): Single<UserModel>

    //다른 유저 정보 불러오기
    @GET("{userID}/")
    fun getOtherUserInfo(
        @Header("x-access-token") token: String,
        @Path("userID") userID: String
    ): Single<UserModel>

    //마이페이지 내가 올린 글 불러오기
    //완료
//    @GET("myPage/")
//    fun getMyPost(
//        @Header("x-access-token") token: String
//    ): Single<UserModel>

    //내가 좋아요 누른 글 보기
    @GET("myLike/")
    fun getMyLike(
        @Header("Authorization") token: String
    ): Single<ArrayList<MyPostModel>>

    //팔로워 불러오기
    //완료
    @GET("{userID}/followerPath/")
    @Headers("Content-Type:application/json")
    fun getFollower(@Header ("Authorization") token: String, @Path("userID") userID: String): Single<ArrayList<FollowerModel>>

    //팔로잉 불러오기
    //완료
    @GET("{userID}/followingPath/")
    @Headers("Content-Type:application/json")
    fun getFollowing(@Header ("Authorization") token: String, @Path("userID") userID: String): Single<ArrayList<FollowerModel>>

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
    fun getReply(@Header("Authorization") token: String, @Path("postCode") postCode: Int): Single<ArrayList<ReplyListModel>>

    //댓글 작성
    @POST("/{postCode}/reply/")
    fun postReply(
        @Header("Authorization") token: String,
        @Path("postCode") postCode: Int,
        @Body body: Any?): Call<Unit>

    //프로필 수정
    //완료
    @Multipart
    @PATCH("Mypage/update_info/")
    fun profileEdit(
        @Header("Authorization") token: String,
        @Part("username") username: RequestBody,
        @Part("birth") birth: RequestBody
    ): Call<Unit>

    //마이페이지에서 고른 글 보기
    @GET("content/")
    fun getContent(
        @Header("Authorization") token: String
    ): Single<FeedModel>
}
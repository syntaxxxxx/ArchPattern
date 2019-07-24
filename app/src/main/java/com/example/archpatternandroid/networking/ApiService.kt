package com.example.archpatternandroid.networking

import com.example.archpatternandroid.entity.ResponseLogin
import com.example.archpatternandroid.entity.ResponseRegister
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    /**
     * read this link https://square.github.io/retrofit/
     * */

    @Multipart
    @POST("get-pengguna-add.php")
    fun regis(
        @Part("nama") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("level") level: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<ResponseRegister>


    @FormUrlEncoded
    @POST("get-pengguna-login.php")
    fun login(
        @Field("nama") name: String,
        @Field("passowrd") password: String
    ): Call<ResponseLogin>

}
package com.example.archpatternandroid.repository

import com.example.archpatternandroid.entity.ResponseLogin
import com.example.archpatternandroid.entity.ResponseRegister
import com.example.archpatternandroid.networking.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

interface RegisterRepository {

    val service: ApiService

    fun regis(
        name: RequestBody,
        email: RequestBody,
        password: RequestBody,
        level: RequestBody,
        images: MultipartBody.Part
    ): Call<ResponseRegister>

}
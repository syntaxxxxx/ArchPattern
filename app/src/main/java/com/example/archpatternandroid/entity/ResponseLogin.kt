package com.example.archpatternandroid.entity

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
package com.example.archpatternandroid.entity

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
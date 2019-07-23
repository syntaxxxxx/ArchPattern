package com.example.archpatternandroid.entity

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
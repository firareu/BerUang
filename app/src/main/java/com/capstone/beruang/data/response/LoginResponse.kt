package com.capstone.beruang.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("userId")
	val userId: String
)

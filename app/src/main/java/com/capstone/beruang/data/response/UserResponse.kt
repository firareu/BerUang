package com.capstone.beruang.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("user")
	val user: UserItem
)

data class UserItem(

	@field:SerializedName("uid")
	val uid: String,

	@field:SerializedName("profilePicture")
	val profilePicture: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("dob")
	val dob: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)

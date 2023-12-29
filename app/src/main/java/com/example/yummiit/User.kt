package com.example.yummiit

data class User(
    val id: Int = 0,
    val email:String="",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

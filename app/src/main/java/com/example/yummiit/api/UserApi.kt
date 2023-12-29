package com.example.yummiit.api

import com.example.yummiit.User
import com.google.gson.Gson

class UserApi
{
    companion object
    {
        fun register(user: User): ApiResponse = Api.post("users/register.php", Gson().toJson(user))
        fun login(user:User) : ApiResponse = Api.post("users/login.php", Gson().toJson(user))

        fun ApiResponse.asUser(): User = Gson().fromJson(this.json, User::class.java)
    }
}
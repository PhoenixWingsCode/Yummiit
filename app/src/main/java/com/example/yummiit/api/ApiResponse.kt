package com.example.yummiit.api

import com.google.gson.Gson

data class ApiResponse(val statusCode: Int, val json: String)
{
    fun getError(): ApiError
    {
        val error = Gson().fromJson(json, ApiError::class.java)
        return error
    }
}
package com.example.yummiit.api

import java.net.HttpURLConnection
import java.net.URL

class Api
{
    companion object
    {
        private const val BASE_URL = "http://10.0.2.2/api"

        fun get(path: String, toJson: String): ApiResponse
        {
            val url = "$BASE_URL/$path"
            val connection = URL(url).openConnection() as HttpURLConnection

            connection.requestMethod = "GET"
            connection.doInput = true
            connection.doOutput = true

            val reader = if (connection.responseCode == HttpURLConnection.HTTP_OK) connection.inputStream.bufferedReader() else connection.errorStream.bufferedReader()
            val response = reader.readText()

            reader.close()

            return ApiResponse(connection.responseCode, response)
        }

        fun post(path: String, data: String): ApiResponse
        {
            val url = "$BASE_URL/$path"
            val connection = URL(url).openConnection() as HttpURLConnection

            connection.requestMethod = "POST"
            connection.doInput = true
            connection.doOutput = true

            val writer = connection.outputStream.bufferedWriter()
            writer.write(data)
            writer.close()

            val reader = if (connection.responseCode == HttpURLConnection.HTTP_OK)
                connection.inputStream.bufferedReader()
            else
                connection.errorStream.bufferedReader()
            val response = reader.readText()

            reader.close()
            return ApiResponse(connection.responseCode, response)
        }
    }
}
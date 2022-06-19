package com.example.teesas.api

import com.example.teesas.data.AuthResponse
import com.example.teesas.data.UserRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

//retrofit api instance
interface ApiInstance {
    //api call header
    @Headers("Content-Type: application/json")

    //api call method- get
    @GET("1a2eae07-997f-4edc-a756-563e3e4536e4")
    suspend fun loginUser() : Response<AuthResponse>
}
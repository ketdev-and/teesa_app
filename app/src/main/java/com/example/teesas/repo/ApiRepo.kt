package com.example.teesas.repo

import com.example.teesas.api.ApiInstance
import com.example.teesas.data.AuthResponse
import com.example.teesas.data.UserRequest
import javax.inject.Inject

class ApiRepo @Inject constructor(val apiInstance:ApiInstance) {
    suspend fun loginUser() = apiInstance.loginUser()
}
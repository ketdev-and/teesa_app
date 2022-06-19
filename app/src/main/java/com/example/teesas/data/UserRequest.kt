package com.example.teesas.data


//users request data class
data class UserRequest(
    val phoneNumber:String,
    val password:String,
    val isAuth:Boolean
)

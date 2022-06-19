package com.example.teesas.data

import com.google.gson.annotations.Expose
import java.util.*
import javax.annotation.Nullable


//api response data class
data class AuthResponse(
    val status: Boolean,
    val message:Nullable
)

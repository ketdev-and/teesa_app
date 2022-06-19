package com.example.teesas.unit

import androidx.lifecycle.ViewModel
import com.example.teesas.viewmodel.ApiViewmodel
import kotlin.reflect.typeOf

object LoginUnitTest {
    fun loginUser(model:Any, username: String, password: String): Boolean {
        if (model !is ApiViewmodel) {
            return false
        }

        if (username.isEmpty() || password.isEmpty()) {
            return false
        }

        return true
    }
}
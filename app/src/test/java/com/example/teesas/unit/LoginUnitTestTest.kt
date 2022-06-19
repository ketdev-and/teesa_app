package com.example.teesas.unit


import androidx.lifecycle.ViewModel
import com.example.teesas.viewmodel.ApiViewmodel
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginUnitTestTest{
    var fkmodel:fakeViewModel = fakeViewModel()

    @Test
    fun `when model is of ApiViewmodel return false`(){
            val phone = "23457001119"
            val password = "23457001119"
            val result = LoginUnitTest.loginUser(fkmodel, phone, password)
            assertThat(result).isFalse()

    }

    @Test
    fun `when input data is empty return false`(){
        val phone = ""
        val password = "23457001119"
        val result = LoginUnitTest.loginUser(fkmodel, phone, password)
        assertThat(result).isFalse()

    }
}

class fakeViewModel:ViewModel(){

}
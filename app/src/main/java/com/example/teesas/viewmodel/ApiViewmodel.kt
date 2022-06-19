package com.example.teesas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teesas.data.UserRequest
import com.example.teesas.repo.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class ApiViewmodel @Inject constructor(val apiRepo: ApiRepo?) : ViewModel() {
    //    val userAuth = MutableLiveData<AuthResponse>()
    //    val isu : LiveData<AuthResponse>
    //     get() = userAuth

    val isLoading = MutableLiveData<Boolean>()
    val isL: LiveData<Boolean>
        get() = isLoading

    val userAuth = MutableLiveData<UserRequest>()
    val isu: LiveData<UserRequest>
        get() = userAuth

    //view model - authenticate and login user
    fun loginUser(userPhone: String, userPass: String) = viewModelScope.launch(Dispatchers.IO) {
        isLoading.postValue(true)
        setTimer(3000, authenticateUser(userPhone,userPass))



/*        try {
           apiRepo.loginUser().let {
                if(it.isSuccessful){
                    Log.d("Auth", it.body().toString())
                    userAuth.postValue(it.body())
                    isLoading.postValue(false)
                }
            }

        } catch (e: IOException) {
            Log.d("Auth", "IOException ${e.message}")
            isLoading.postValue(false)
        } catch (e: HttpException) {
            Log.d("Auth", "HTTPException ${e.message}")
            isLoading.postValue(false)
        }*/
    }


    private fun setTimer(time: Long, cb:Any){
        Timer("authenticate", false).schedule(time) {
            cb
        }
    }

    private fun authenticateUser(phone:String, pass:String){
        val authRecord = UserRequest(phone, pass, true)
        userAuth.postValue(authRecord)
        isLoading.postValue(false)
    }
}



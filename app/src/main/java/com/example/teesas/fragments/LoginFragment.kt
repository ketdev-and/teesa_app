package com.example.teesas.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.teesas.R
import com.example.teesas.data.AuthResponse
import com.example.teesas.data.UserRequest
import com.example.teesas.databinding.FragmentLoginBinding
import com.example.teesas.viewmodel.ApiViewmodel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    //api view model class
    val model: ApiViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        binding
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        //view model observable for loading sate
       observeLoading()

        //view model observable for auth sate
        observeAuth()

        //login button click
        binding.lgButton.setOnClickListener {
            loginUser(model)
        }
    }

    private fun loginUser(authModel:ApiViewmodel){
        val inputPhone: Editable? = binding.lgPhoneNo.text
        val inputPass: Editable? = binding.lgPassword.text
        authModel.loginUser(inputPhone.toString(), inputPass.toString())
    }

    private fun <T> getObservables(model:LiveData<T>, observer: Observer<T>){
        model.observe(viewLifecycleOwner, observer)
    }

    private fun observeLoading(){
            getObservables<Boolean>(model.isL, Observer {
                if (it){
                    binding.lgButton.text = "Loading..."
                }else{
                    binding.lgButton.text = "Login"
                }
            })
    }

    private fun observeAuth(){
            getObservables<UserRequest>(model.isu, Observer {
                if (it.isAuth){
                    Log.d("Auth", it.toString())
                    val bundle:Bundle = bundleOf("phone" to it.phoneNumber, "pass" to it.password)
                    Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_dashboardFragment, bundle)
                }
            })
    }
}


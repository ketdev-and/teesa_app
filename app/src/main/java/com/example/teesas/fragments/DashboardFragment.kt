package com.example.teesas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.teesas.R
import com.example.teesas.databinding.FragmentDashboardBinding
import com.example.teesas.databinding.FragmentLoginBinding
import com.example.teesas.viewmodel.ApiViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    var _binding: FragmentDashboardBinding? = null
    val binding get() = _binding!!
    val apiViewmodel: ApiViewmodel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp)


        //retrieve navigation args passed from login fragment
        val session = arguments?.getString("phone")
        binding.userSession.text = session

    }

    private fun onSupportNavigateUp(): Boolean {
        return  true
    }
}
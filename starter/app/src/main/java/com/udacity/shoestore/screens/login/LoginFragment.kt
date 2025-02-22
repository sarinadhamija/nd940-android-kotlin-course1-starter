package com.udacity.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.SharedViewModel

class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding?.data = sharedViewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnLogin?.setOnClickListener {
            navigateToNextScreen()
        }
        binding?.tvSignup?.setOnClickListener {
           navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen(){
        val email = binding?.etFieldEmail?.text.toString()
        val password = binding?.etFieldPassword?.text.toString()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            showToast(getString(R.string.error_fields_empty))
        } else {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }

    private fun showToast(message : String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.example.getcloud_kotlin.ui.login

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.getcloud_kotlin.R
import com.example.getcloud_kotlin.databinding.FragmentLoginBinding
import kotlinx.coroutines.delay

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var Email: String
    private lateinit var Password: String
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.signin.setOnClickListener {
            binding.progressBar2.isVisible
            binding.signin.isInvisible
            Email = binding.email.text.toString().trim()
            Password = binding.password.text.toString().trim()
            viewModel.signIN(Email, Password)
            viewModel.checker.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    viewModel.user.observe(viewLifecycleOwner, Observer {
                        if (!it.isEmpty()) {
                            binding.progressBar2.isInvisible
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToHomeFragment(it)
                            findNavController().navigate(action)
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                            binding.signin.isVisible
                        }
                    })

                } else {

                }
            })

        }
        binding.newaccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }
        return binding.root
    }


}
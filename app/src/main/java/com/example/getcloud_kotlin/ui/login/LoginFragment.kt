package com.example.getcloud_kotlin.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.getcloud_kotlin.R
import com.example.getcloud_kotlin.databinding.FragmentLoginBinding

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
        binding.lifecycleOwner = this
        binding.signin.setOnClickListener {
            Email = binding.email.text.toString().trim()
            Password = binding.password.text.toString().trim()
            viewModel.signIN(Email, Password)
        }
        binding.newaccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }
        viewModel.user.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(it)
                findNavController().navigate(action)
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar2.visibility = View.VISIBLE
                binding.signin.visibility = View.INVISIBLE
            } else {
                binding.progressBar2.visibility = View.INVISIBLE
                binding.signin.visibility = View.VISIBLE
            }
        })
        return binding.root
    }


}
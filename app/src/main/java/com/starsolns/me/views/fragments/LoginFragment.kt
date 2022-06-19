package com.starsolns.me.views.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.starsolns.me.R
import com.starsolns.me.data.datastore.SessionManager
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentLoginBinding
import com.starsolns.me.model.UserLogin
import com.starsolns.me.util.Settings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var settings: Settings
    private lateinit var dialog: ProgressDialog

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref: SharedPreferences =
            requireActivity().getSharedPreferences("me_prefs", Context.MODE_PRIVATE)
        settings = Settings(pref)
        settings = Settings(pref)

        val isLogged = settings.isLoggedIn()
        if (isLogged) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val pref: SharedPreferences = requireActivity().getSharedPreferences("me_prefs", Context.MODE_PRIVATE)
        settings = Settings(pref)

        Toast.makeText(requireContext(), settings.getBearerToken(), Toast.LENGTH_LONG).show()


        binding.login.setOnClickListener {
            validateInputsAndLogin()
        }



        return binding.root
    }

    private fun validateInputsAndLogin() {
        val email = binding.loginEmail.text.toString().trim()
        val password = binding.loginPassword.text.toString()

        if(email.isEmpty() || password.isEmpty()){
           Toast.makeText(requireContext(), "Please fill all the blanks", Toast.LENGTH_LONG).show()
        } else {
        loginUser(email, password)
        }

    }

    private fun loginUser(email: String, password: String) {
        dialog = ProgressDialog(requireActivity())
        dialog.show()
        dialog.setContentView(R.layout.progress_layout)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        lifecycleScope.launch {
        mainViewModel.loginUser(UserLogin(email, password))
        mainViewModel.loginResponse.observe(viewLifecycleOwner){
            dialog.dismiss()
            settings.setBearerToken(it.token)
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.starsolns.me.views.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.starsolns.me.R
import com.starsolns.me.data.datastore.SessionManager
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentRegisterBinding
import com.starsolns.me.model.UserRegister
import com.starsolns.me.util.NetworkResult
import com.starsolns.me.util.Settings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment() : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    private lateinit var settings: Settings
    private lateinit var dialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val pref: SharedPreferences = requireActivity().getSharedPreferences("me_prefs", Context.MODE_PRIVATE)
        settings = Settings(pref)

        binding.register.setOnClickListener {
               validateInputs()
        }


        return binding.root
    }

    private fun validateInputs() {
        val name = binding.fullName.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.phone.text.toString()
        val password = binding.password.text.toString()

        if(name.isEmpty() or email.isEmpty() or phone.isEmpty() or password.isEmpty()){
            Toast.makeText(requireContext(), "Please fill all the blanks", Toast.LENGTH_LONG).show()
        } else {
            Log.i("TAG", "Button clicked")
            //registerUser(name,email, phone, password)
            //registerUserCall(name,email, phone, password)
            //registerUserCall2(name, email, phone, password)
            registerUser(name, email, phone, password)
        }
    }

    private fun registerUser(name: String, email: String, phone: String, password: String) {
        dialog = ProgressDialog(requireActivity())
        dialog.show()
        dialog.setContentView(R.layout.progress_layout)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        lifecycleScope.launch {
            mainViewModel.registerUser(UserRegister(name, email, phone, password))

            mainViewModel.registerResponse.observe(viewLifecycleOwner){
                    settings.setBearerToken(it.token)
                    settings.setIsLoggedIn(true)
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                dialog.dismiss()
                Log.i("TAG", it.token)
            }
        }
    }


    private fun registerUserCall(name: String,email: String, phone: String, password: String) {
            mainViewModel.registerUserCall(name, email, phone, password)

            mainViewModel.registerResponse.observe(viewLifecycleOwner){
               // findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
               //Toast.makeText(requireContext(), it.token.toString(), Toast.LENGTH_LONG).show()
                Log.i("TAG", it.success.toString())

            }
    }

    private fun registerUserCall2(name: String,email: String, phone: String, password: String) {
        mainViewModel.registerUserCall2(UserRegister(name, email, phone, password))

        mainViewModel.registerResponse.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            Toast.makeText(requireContext(), it.token.toString(), Toast.LENGTH_LONG).show()
            Log.i("TAG", it.success.toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

///https://github.com/jmngige/Me.git
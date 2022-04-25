package com.starsolns.me.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.starsolns.me.R
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentRegisterBinding
import com.starsolns.me.model.UserRegister
import com.starsolns.me.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

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
        }

        registerUser(email,name, password, phone)
    }

    private fun registerUser(email: String, name: String, password: String , phone: String) {
        mainViewModel.registerUser(UserRegister(email,  name, password, phone))
      mainViewModel.registerResponse.observe(viewLifecycleOwner){
          when(it){
              is NetworkResult.Success -> {
                  findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
              }
              is NetworkResult.Error -> {
                  Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_LONG).show()
              }
          }
      }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

///https://github.com/jmngige/Me.git
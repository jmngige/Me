package com.starsolns.me.views.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.starsolns.me.R
import com.starsolns.me.data.datastore.SessionManager
import com.starsolns.me.databinding.FragmentLoginBinding
import com.starsolns.me.util.Settings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var settings: Settings

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val pref: SharedPreferences = requireActivity().getSharedPreferences("me_prefs", Context.MODE_PRIVATE)
        settings = Settings(pref)

        Toast.makeText(requireContext(), settings.getBearerToken(), Toast.LENGTH_LONG).show()


        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.starsolns.me.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.starsolns.me.R
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mainViewModel.loading.observe(viewLifecycleOwner){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        mainViewModel.userProfile.observe(viewLifecycleOwner){
            binding.userFullName.text = it.user.fullName
            binding.userEmail.text = it.user.email
            binding.userPhone.text = it.user.phone
            binding.userRole.text = it.user.role
        }

        mainViewModel.getProfile()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
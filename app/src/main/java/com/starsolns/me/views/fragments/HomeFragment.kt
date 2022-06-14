package com.starsolns.me.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.me.R
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentHomeBinding
import com.starsolns.me.util.NotificationHelper
import com.starsolns.me.views.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var usersAdapter: UsersAdapter
    private var profileId: String = "620a9c721a39794dfdd69c60"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        usersAdapter = UsersAdapter(requireContext())

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.users.observe(viewLifecycleOwner){users->
            users?.let {
                usersAdapter.setData(it.users)
                //Toast.makeText(requireContext(), "Users retrieved successfully", Toast.LENGTH_SHORT).show()
            }
        }


        mainViewModel.userProfile.observe(viewLifecycleOwner){profile->
            profile?.let {
               // Toast.makeText(requireContext(), it.profileResponse.fullName.toString(), Toast.LENGTH_SHORT).show()
            }
        }


        binding.profile.setOnClickListener {
            //findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            val myNotification = NotificationHelper(requireContext())
            myNotification.createNotification("James", "james@gmail.com")

        }

        binding.usersListRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.usersListRv.adapter = usersAdapter

        mainViewModel.getAllUsers()



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
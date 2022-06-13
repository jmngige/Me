package com.starsolns.me.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentHomeBinding
import com.starsolns.me.views.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var profileId: String

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
            profileId = profile.profileResponse.id
            Toast.makeText(requireContext(), "Profile retrieved successfully", Toast.LENGTH_SHORT).show()
            //Toast.makeText(requireContext(), profile.fullName, Toast.LENGTH_SHORT).show()
            }
        }


        binding.profile.setOnClickListener {
            mainViewModel.getProfile(profileId)
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
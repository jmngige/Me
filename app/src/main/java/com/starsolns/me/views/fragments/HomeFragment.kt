package com.starsolns.me.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.me.R
import com.starsolns.me.data.viewmodel.MainViewModel
import com.starsolns.me.databinding.FragmentHomeBinding
import com.starsolns.me.views.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var usersAdapter: UsersAdapter

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
                usersAdapter.setData(it)
            }
        }

        binding.usersListRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.usersListRv.adapter = usersAdapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
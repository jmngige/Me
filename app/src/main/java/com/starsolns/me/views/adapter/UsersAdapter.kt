package com.starsolns.me.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starsolns.me.databinding.UserItemLayoutBinding
import com.starsolns.me.model.Users

class UsersAdapter(
    private val context: Context,
    private val usersList: ArrayList<Users>
): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val customBinding: UserItemLayoutBinding = UserItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(customBinding)
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
       val currentUser = usersList[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class ViewHolder(binding: UserItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        val fullName = binding.fullName
        val email = binding.email
        val phone = binding.phone
        val role = binding.role

        fun bind(user: Users){
            fullName.text = user.fullName
            email.text = user.email
            phone.text = "${user.phone}"
            role.text = user.role
        }

    }

}
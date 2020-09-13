package com.github.af2905.listofusers.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.af2905.listofusers.databinding.UserItemBinding
import com.github.af2905.listofusers.presentation.item.IUserClickListener
import com.github.af2905.listofusers.presentation.item.UserViewHolder
import com.github.af2905.listofusers.repository.database.entity.UserEntity

class AllUsersAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private var users: MutableList<UserEntity> = mutableListOf()
    private lateinit var clickListener: IUserClickListener<UserEntity>

    fun getUsers() = users

    fun setUsers(users: List<UserEntity>) {
        this.users = users as MutableList<UserEntity>
    }

    fun setClickListener(clickListener: IUserClickListener<UserEntity>) {
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: UserItemBinding = UserItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.tag = users[position]
        holder.bind(users[position], clickListener)
    }
}


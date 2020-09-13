package com.github.af2905.listofusers.presentation.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.af2905.listofusers.databinding.UserItemBinding
import com.github.af2905.listofusers.repository.database.entity.UserEntity

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var userEntity: UserEntity
    private lateinit var clickListener: IUserClickListener<UserEntity>
    private val openDetail = View.OnClickListener { clickListener.openDetail(userEntity) }

    fun bind(userEntity: UserEntity, clickListener: IUserClickListener<UserEntity>) {
        this.userEntity = userEntity
        this.clickListener = clickListener
        setupItem()
    }

    private fun setupItem() {
        binding.user = userEntity
        binding.root.setOnClickListener(openDetail)
    }
}
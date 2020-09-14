package com.github.af2905.listofusers.presentation.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.af2905.listofusers.databinding.UserItemBinding
import com.github.af2905.listofusers.repository.database.entity.UserEntity

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var userEntity: UserEntity
    private lateinit var userClickListener: IUserClickListener<UserEntity>
    private lateinit var deleteClickListener: IDeleteClickListener<UserEntity>

    private val openDetail = View.OnClickListener { userClickListener.openDetail(userEntity) }
    private val deleteUser = View.OnClickListener { deleteClickListener.deleteUser(userEntity) }


    fun bind(
        userEntity: UserEntity,
        userClickListener: IUserClickListener<UserEntity>,
        deleteClickListener: IDeleteClickListener<UserEntity>
    ) {
        this.userEntity = userEntity
        this.userClickListener = userClickListener
        this.deleteClickListener = deleteClickListener
        setupItem()
    }

    private fun setupItem() {
        binding.user = userEntity
        binding.root.setOnClickListener(openDetail)
        binding.btnDeleteUser.setOnClickListener(deleteUser)
    }
}
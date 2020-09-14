package com.github.af2905.listofusers.utils

import androidx.recyclerview.widget.DiffUtil
import com.github.af2905.listofusers.repository.database.entity.UserEntity

class AllUsersDiffUtilCallback(
    private val oldList: List<UserEntity>,
    private val newList: List<UserEntity>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].firstName == newList[newItemPosition].firstName &&
                oldList[oldItemPosition].lastName == newList[newItemPosition].lastName &&
                oldList[oldItemPosition].email == newList[newItemPosition].email
    }
}
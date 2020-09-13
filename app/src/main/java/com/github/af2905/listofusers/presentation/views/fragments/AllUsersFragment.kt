package com.github.af2905.listofusers.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.af2905.listofusers.R
import com.github.af2905.listofusers.databinding.FragmentAllUsersBinding
import com.github.af2905.listofusers.di.component.ViewModelComponent
import com.github.af2905.listofusers.presentation.adapter.AllUsersAdapter
import com.github.af2905.listofusers.presentation.base.BaseFragment
import com.github.af2905.listofusers.presentation.decoration.DivItemDecoration
import com.github.af2905.listofusers.presentation.item.IUserClickListener
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.github.af2905.listofusers.viewmodel.AllUsersViewModel
import kotlinx.android.synthetic.main.fragment_all_users.view.*
import javax.inject.Inject

class AllUsersFragment : BaseFragment() {
    private lateinit var dataBinding: FragmentAllUsersBinding
    private lateinit var recycler: RecyclerView
    private val adapter = AllUsersAdapter()
    private val clickListener: IUserClickListener<UserEntity> =
        object : IUserClickListener<UserEntity> {
            override fun openDetail(m: UserEntity) {
                TODO("Not yet implemented")
            }
        }

    var viewModel: AllUsersViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_users, container, false)
        val view = dataBinding.root
        viewModel?.getLiveDataAllUsers()
            ?.observe(viewLifecycleOwner, { initRecyclerView(view, adapter, it) })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* view.findViewById<Button>(R.id.button_first).setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/
    }

    private fun initRecyclerView(view: View, adapter: AllUsersAdapter, users: List<UserEntity>) {
        adapter.setUsers(users)
        recycler = view.recyclerViewAllUsers
        adapter.setClickListener(clickListener)
        recycler.adapter = adapter
        recycler.addItemDecoration(DivItemDecoration(16, 8))
    }
}
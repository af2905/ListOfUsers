package com.github.af2905.listofusers.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.af2905.listofusers.R
import com.github.af2905.listofusers.databinding.FragmentAllUsersBinding
import com.github.af2905.listofusers.di.component.ViewModelComponent
import com.github.af2905.listofusers.presentation.adapter.AllUsersAdapter
import com.github.af2905.listofusers.presentation.base.BaseFragment
import com.github.af2905.listofusers.presentation.decoration.DivItemDecoration
import com.github.af2905.listofusers.presentation.item.IDeleteClickListener
import com.github.af2905.listofusers.presentation.item.IUserClickListener
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.github.af2905.listofusers.utils.AllUsersDiffUtil
import com.github.af2905.listofusers.viewmodel.AllUsersViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_all_users.view.*
import javax.inject.Inject

class AllUsersFragment : BaseFragment() {
    private lateinit var dataBinding: FragmentAllUsersBinding
    private lateinit var recycler: RecyclerView
    private val adapter = AllUsersAdapter()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val disposeBag = CompositeDisposable()
    private val userClickListener: IUserClickListener<UserEntity> =
        object : IUserClickListener<UserEntity> {
            override fun openDetail(m: UserEntity) {
                openSingleUserDetailInfo(m)
            }
        }
    private val deleteClickListener: IDeleteClickListener<UserEntity> =
        object : IDeleteClickListener<UserEntity> {
            override fun deleteUser(m: UserEntity) {
                deleteUserClick(m)
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
        initRecyclerView(view, adapter)
        viewModel?.loadAllUsersFromDatabase()
        loadDataFromViewModel()
        swipeRefreshLayout = view.swipeAllUsersRefreshLayout
        updateDatabaseWhenSwipe()
        return view
    }

    private fun initRecyclerView(view: View, adapter: AllUsersAdapter) {
        adapter.setUserClickListener(userClickListener)
        adapter.setDeleteClickListener(deleteClickListener)
        recycler = view.recyclerViewAllUsers
        recycler.adapter = adapter
        recycler.addItemDecoration(DivItemDecoration(16, 8))
    }

    private fun loadDataFromViewModel() {
        viewModel?.getLiveDataAllUsers()
            ?.observe(viewLifecycleOwner, { setDataInAdapter(adapter, it) })
    }

    private fun setDataInAdapter(adapter: AllUsersAdapter, users: List<UserEntity>): Disposable {
        val listOfUsers: Observable<List<UserEntity>> = Observable.fromArray(users)
        val disposable = listOfUsers
            .map { DiffUtil.calculateDiff(AllUsersDiffUtil(adapter.getUsers(), it)) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { adapter.setUsers(users) }
            .subscribe { it.dispatchUpdatesTo(adapter) }
        disposeBag.add(disposable)
        return disposable
    }

    private fun openSingleUserDetailInfo(userEntity: UserEntity) {
        val bundle = Bundle()
        bundle.putInt("id", userEntity.id)
        findNavController().navigate(R.id.action_AllUsersFragment_to_SingleUserFragment, bundle)
    }

    private fun deleteUserClick(userEntity: UserEntity) {
        adapter.removeUser(userEntity)
        viewModel?.deleteUserFromDatabase(userEntity)
    }

    private fun updateDatabaseWhenSwipe() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel?.updateDatabase()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onStop() {
        super.onStop()
        disposeBag.dispose()
    }
}
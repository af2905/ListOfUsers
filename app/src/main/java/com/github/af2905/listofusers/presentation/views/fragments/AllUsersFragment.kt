package com.github.af2905.listofusers.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.github.af2905.listofusers.R
import com.github.af2905.listofusers.databinding.FragmentAllUsersBinding
import com.github.af2905.listofusers.di.component.ViewModelComponent
import com.github.af2905.listofusers.presentation.base.BaseFragment
import com.github.af2905.listofusers.viewmodel.AllUsersViewModel
import javax.inject.Inject

class AllUsersFragment : BaseFragment() {
    lateinit var dataBinding: FragmentAllUsersBinding

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
        viewModel?.loadAllUsersFromNetwork()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* view.findViewById<Button>(R.id.button_first).setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/
    }
}
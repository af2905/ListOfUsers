package com.github.af2905.listofusers.presentation.views.fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.github.af2905.listofusers.R
import com.github.af2905.listofusers.databinding.FragmentSingleUserBinding
import com.github.af2905.listofusers.di.component.ViewModelComponent
import com.github.af2905.listofusers.presentation.base.BaseFragment
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.github.af2905.listofusers.viewmodel.SingleUserViewModel
import kotlinx.android.synthetic.main.fragment_single_user.*
import kotlinx.android.synthetic.main.fragment_single_user.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class SingleUserFragment : BaseFragment() {
    private lateinit var dataBinding: FragmentSingleUserBinding
    private lateinit var userName: EditText
    private lateinit var userLastName: EditText
    private lateinit var userEmail: EditText
    private var userId by Delegates.notNull<Int>()
    private var userAvatar: String? = null
    private var isEditTextButtonClicked = false

    var viewModel: SingleUserViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_single_user, container, false)
        val view = dataBinding.root
        initViews(view)
        userId = requireArguments().getInt("id")
        viewModel?.getUser(userId)
        viewModel?.getLiveDataUser()?.observe(viewLifecycleOwner, {
            dataBinding.user = it
            userAvatar = it.avatar
        })
        return view
    }

    private fun initViews(view: View) {
        userName = view.editTxtUserName
        userLastName = view.editTxtUserLastName
        userEmail = view.editTxtUserEmail
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val theme = view.context.theme

        val closeDetail = btnCloseDetail
        closeDetail.setOnClickListener {
            findNavController().navigate(R.id.action_SingleUserFragment_to_AllUsersFragment)
        }

        val editDetail = btnEditDetail
        editDetail.setOnClickListener {
            isEditTextButtonClicked = !isEditTextButtonClicked
            when (isEditTextButtonClicked) {
                true -> {
                    setEditTextConfig(true, theme)
                    editDetail.icon = resources.getDrawable(R.drawable.ic_check_circle, theme)
                }
                false -> {
                    setEditTextConfig(false, theme)
                    editDetail.icon = resources.getDrawable(R.drawable.ic_account_edit, theme)
                    val user = UserEntity(
                        userId,
                        userEmail.text.toString(),
                        userName.text.toString(),
                        userLastName.text.toString(),
                        this.userAvatar
                    )
                    viewModel?.updateUser(user)
                }
            }
        }
    }

    private fun setEditTextConfig(boolean: Boolean, theme: Resources.Theme) {
        userName.isEnabled = boolean
        userLastName.isEnabled = boolean
        userEmail.isEnabled = boolean

        when (boolean) {
            true -> setEditTextColor(R.color.colorAccent, theme)
            false -> setEditTextColor(R.color.colorPrimary, theme)
        }
    }

    private fun setEditTextColor(color: Int, theme: Resources.Theme) {
        userName.setTextColor(resources.getColor(color, theme))
        userLastName.setTextColor(resources.getColor(color, theme))
        userEmail.setTextColor(resources.getColor(color, theme))
    }
}
package com.github.af2905.listofusers.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.af2905.listofusers.App
import com.github.af2905.listofusers.di.component.ViewModelComponent

abstract class BaseFragment : Fragment() {
    private lateinit var activity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    private fun createDaggerDependencies() =
        injectDependency((activity.application as App).getViewModelComponent())

    abstract fun injectDependency(component: ViewModelComponent)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }
}
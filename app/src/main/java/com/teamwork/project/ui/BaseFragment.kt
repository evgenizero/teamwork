package com.teamwork.project.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = savedInstanceState?.let {
            initVM(savedInstanceState)
        } ?: run {
            initVM()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lifecycle.addObserver(viewModel)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected abstract fun initVM(): VM

    protected open fun initVM(savedInstanceState: Bundle): VM = initVM()

    protected fun startFragment(fragment: BaseFragment<*>,
                      addToBackStack: Boolean = true) {
        val baseActivity = activity as? BaseActivity
        if (baseActivity != null) {
            val transaction = baseActivity.supportFragmentManager.beginTransaction()
            transaction.replace(baseActivity.fragmentContainer, fragment, fragment.tag)
            if (addToBackStack) {
                transaction.addToBackStack(null)
            }
            transaction.commit()
        }
    }
}
package com.teamwork.project.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    protected val disposableContainer: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        disposableContainer.dispose()
        disposableContainer.clear()
    }
}
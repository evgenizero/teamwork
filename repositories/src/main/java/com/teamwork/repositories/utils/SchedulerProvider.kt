package com.teamwork.repositories.utils

import io.reactivex.Flowable
import io.reactivex.Scheduler

class SchedulerProvider (
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler) {

    fun <T> getSchedulersForFlowable(): (Flowable<T>) -> Flowable<T> {
        return { flowable: Flowable<T> ->
            flowable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }
}
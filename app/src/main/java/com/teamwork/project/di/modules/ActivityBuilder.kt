package com.teamwork.project.di.modules

import com.teamwork.project.di.PerActivity
import com.teamwork.project.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [ContributesMainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}

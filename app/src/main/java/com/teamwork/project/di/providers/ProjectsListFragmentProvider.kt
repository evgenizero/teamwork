package com.teamwork.project.di.providers

import com.teamwork.project.ui.projects.ProjectsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProjectsListFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideSelectPaymentTypeFragment(): ProjectsListFragment

}

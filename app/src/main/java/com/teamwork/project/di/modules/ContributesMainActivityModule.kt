package com.teamwork.project.di.modules

import androidx.lifecycle.ViewModel
import com.teamwork.project.di.ViewModelKey
import com.teamwork.project.di.providers.ProjectDetailsFragmentProvider
import com.teamwork.project.di.providers.ProjectsListFragmentProvider
import com.teamwork.project.ui.projectDetails.ProjectDetailsFragment
import com.teamwork.project.ui.projectDetails.ProjectDetailsViewModel
import com.teamwork.project.ui.projects.ProjectsListFragment
import com.teamwork.project.ui.projects.ProjectsListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by evgeniy on 12/11/18.
 */

@Module(includes = [ActivityModule::class])
abstract class ContributesMainActivityModule {

    @ContributesAndroidInjector(modules = [ProjectsListFragmentProvider::class])
    abstract fun provideProjectsListFragment(): ProjectsListFragment

    @ContributesAndroidInjector(modules = [ProjectDetailsFragmentProvider::class])
    abstract fun provideProjectDetailsFragment(): ProjectDetailsFragment

    @Module
    abstract class ProjectsListViewModelProvider {
        @Binds
        @IntoMap
        @ViewModelKey(ProjectsListViewModel::class)
        abstract fun bindProjectsLiveViewModel(viewModel: ProjectsListViewModel): ViewModel
    }

    @Module
    abstract class ProjectDetailsViewModelProvider {
        @Binds
        @IntoMap
        @ViewModelKey(ProjectDetailsViewModel::class)
        abstract fun bindProjectDetailsViewModel(viewModel: ProjectDetailsViewModel): ViewModel
    }
}

package com.teamwork.project.di.modules;

import androidx.lifecycle.ViewModel;
import com.teamwork.project.di.ViewModelKey;
import com.teamwork.project.ui.projectDetails.ProjectDetailsViewModel;
import com.teamwork.project.ui.projects.ProjectsListViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsProvider {

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsListViewModel.class)
    abstract ViewModel bindProjectsLiveViewModel(ProjectsListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProjectDetailsViewModel.class)
    abstract ViewModel bindProjectDetailsViewModel(ProjectDetailsViewModel viewModel);
}

package com.teamwork.repositories.di.modules;

import com.teamwork.network.services.ProjectsService;
import com.teamwork.repositories.ProjectsRepository;
import com.teamwork.repositories.ProjectsRepositoryImpl;
import com.teamwork.repositories.utils.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ProjectsRepository provideProjectsRepository(final ProjectsService projectsService,
                                                        final SchedulerProvider schedulerProvider) {
        return new ProjectsRepositoryImpl(projectsService, schedulerProvider);
    }
}

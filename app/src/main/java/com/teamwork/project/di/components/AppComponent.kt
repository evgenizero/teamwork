package com.teamwork.project.di.components

import android.app.Application
import com.teamwork.network.di.modules.NetworkModule
import com.teamwork.project.TeamworkApp
import com.teamwork.project.di.modules.ActivityBuilder
import com.teamwork.project.di.modules.AppModule
import com.teamwork.repositories.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<TeamworkApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}

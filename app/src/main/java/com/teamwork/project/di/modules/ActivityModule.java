package com.teamwork.project.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.teamwork.project.di.PerActivity;
import com.teamwork.project.di.factories.ViewModelProviderFactory;
import com.teamwork.project.utils.DateUtils;
import com.teamwork.project.utils.DateUtilsImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Provider;
import java.util.Map;

@Module(includes = ViewModelsProvider.class)
public class ActivityModule {

    @Provides
    @PerActivity
    ViewModelProvider.Factory provideViewModelProviderFactory(final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        return new ViewModelProviderFactory(creators);
    }

    @Provides
    @PerActivity
    DateUtils provideDateUtils() {
        return new DateUtilsImpl();
    }
}

package com.teamwork.project.ui.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teamwork.project.ui.BaseViewModel
import com.teamwork.project.utils.SingleLiveEvent
import com.teamwork.repositories.ProjectsRepository
import com.teamwork.repositories.models.Project
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

data class ProjectListState(
    var loading: Boolean = false,
    var loaded: Boolean = false,
    var projects: List<Project> = emptyList(),
    var error: Boolean = false
)

class ProjectsListViewModel @Inject constructor(val repository: ProjectsRepository
) : BaseViewModel() {

    private val _projectsLiveData = SingleLiveEvent<ProjectListState>()
    val projectsLiveData: LiveData<ProjectListState>
        get() = _projectsLiveData

    private val projectListState = ProjectListState()

    fun loadProjects() {
        repository
            .getActiveProjects()
            .doOnSubscribe {
                _projectsLiveData.value = projectListState.copy(loading = true, loaded = false, error = false)
            }
            .map {
                _projectsLiveData.value = projectListState.copy(loading = false, loaded = true, error = false, projects = it)
            }
            .onErrorReturn {
                _projectsLiveData.value = projectListState.copy(loading = false, loaded = false, error = true)
            }
            .subscribe()
            .addTo(disposableContainer)
    }
}
package com.teamwork.project.ui.projectDetails

import androidx.lifecycle.LiveData
import com.teamwork.project.ui.BaseViewModel
import com.teamwork.project.utils.SingleLiveEvent
import com.teamwork.repositories.ProjectsRepository
import com.teamwork.repositories.models.ProjectDetails
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

data class ProjectDetailsState(
    var loading: Boolean = false,
    var loaded: Boolean = false,
    var project: ProjectDetails? = null,
    var error: Boolean = false
)

class ProjectDetailsViewModel @Inject constructor(
    private val repository: ProjectsRepository
) : BaseViewModel() {

    private val _projectLiveData = SingleLiveEvent<ProjectDetailsState>()
    val projectLiveData: LiveData<ProjectDetailsState>
        get() = _projectLiveData

    private val projectDetailsState = ProjectDetailsState()

    fun loadProject(projectId: String) {
        repository
            .getProjectDetails(projectId)
            .doOnSubscribe {
                _projectLiveData.value = projectDetailsState.copy(loading = true, loaded = false, error = false)
            }
            .map {
                _projectLiveData.value = projectDetailsState.copy(loading = false, loaded = true, error = false, project = it)
            }
            .onErrorReturn {
                _projectLiveData.value = projectDetailsState.copy(loading = false, loaded = false, error = true)
            }
            .subscribe()
            .addTo(disposableContainer)
    }
}
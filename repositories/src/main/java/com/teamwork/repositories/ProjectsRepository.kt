package com.teamwork.repositories

import com.teamwork.network.services.ProjectsService
import com.teamwork.repositories.models.Project
import com.teamwork.repositories.models.ProjectDetails
import com.teamwork.repositories.utils.SchedulerProvider
import io.reactivex.Flowable

interface ProjectsRepository {

    fun getActiveProjects(): Flowable<List<Project>>

    fun getProjectDetails(projectId: String): Flowable<ProjectDetails>
}

class ProjectsRepositoryImpl(
    private val projectsService: ProjectsService,
    private val schedulerProvider: SchedulerProvider
) : ProjectsRepository {

    override fun getProjectDetails(projectId: String): Flowable<ProjectDetails> =
        projectsService
            .getSingleProject(projectId)
            .compose(schedulerProvider.getSchedulersForFlowable())
            .map { ProjectDetails.of(it.project) }

    override fun getActiveProjects() = projectsService
        .getActiveProjects()
        .compose(schedulerProvider.getSchedulersForFlowable())
        .map {
            it.projects.map { projectResponse ->
                Project.of(projectResponse)
            }
        }

}
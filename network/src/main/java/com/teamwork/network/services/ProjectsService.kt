package com.teamwork.network.services

import com.teamwork.network.models.DetailProjectResponse
import com.teamwork.network.models.ProjectsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectsService {

    @GET("projects.json")
    fun getActiveProjects(): Flowable<ProjectsResponse>

    @GET("projects/{id}.json")
    fun getSingleProject(@Path("id") projectId: String): Flowable<DetailProjectResponse>
}
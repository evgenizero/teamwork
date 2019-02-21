package com.teamwork.network.models

import com.google.gson.annotations.SerializedName

class ProjectsResponse(STATUS: NetworkResponseStatus, val projects: List<ProjectResponse>) : NetworkResponse(STATUS)

data class ProjectResponse(
    val id: String,
    val name: String,
    val description: String,
    val company: Company,
    @SerializedName("created-on")
    val createdOn: String,
    val endDate: String?,
    val logo: String?
)
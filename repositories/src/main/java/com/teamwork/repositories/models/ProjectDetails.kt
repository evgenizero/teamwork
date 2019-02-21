package com.teamwork.repositories.models

import com.teamwork.network.models.Company
import com.teamwork.network.models.ProjectResponse
import com.teamwork.network.models.SingleProjectProjectResponse

class ProjectDetails(val id: String,
                     val name: String,
                     val description: String,
                     val company: Company,
                     val createdOn: String,
                     val endDate: String?,
                     val logo: String?) {
    companion object {
        fun of(projectResponse: SingleProjectProjectResponse): ProjectDetails = ProjectDetails(
            projectResponse.id,
            projectResponse.name,
            projectResponse.description,
            projectResponse.company,
            projectResponse.createdOn,
            projectResponse.endDate,
            projectResponse.logo
        )
    }
}
package com.teamwork.network.models

import com.google.gson.annotations.SerializedName

class DetailProjectResponse(status: NetworkResponseStatus,
                            val project: SingleProjectProjectResponse) : NetworkResponse(status)

data class SingleProjectProjectResponse(
    val id: String,
    val name: String,
    val description: String,
    val company: Company,
    @SerializedName("created-on")
    val createdOn: String,
    val endDate: String?,
    val logo: String?
)
package com.teamwork.network.models

import com.google.gson.annotations.SerializedName

abstract class NetworkResponse(val status: NetworkResponseStatus)

enum class NetworkResponseStatus {

    @SerializedName("OK")
    OK
}
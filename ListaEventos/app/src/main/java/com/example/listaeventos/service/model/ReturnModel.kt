package com.example.listaeventos.service.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ReturnModel(
    @SerializedName("code")
    val code: String
)
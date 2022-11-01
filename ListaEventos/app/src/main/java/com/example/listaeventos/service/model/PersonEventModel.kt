package com.example.listaeventos.service.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PersonEventModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("name")
    val name: String
)
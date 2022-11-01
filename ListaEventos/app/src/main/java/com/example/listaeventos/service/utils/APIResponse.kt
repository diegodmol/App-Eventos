package com.example.listaeventos.service.utils

interface APIResponse<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)
}
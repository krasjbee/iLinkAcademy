package com.example.ilinkacademy.utils

sealed class NetworkResource<T>(val data:T?,val message: String? ) {
    class Loading<T> : NetworkResource<T>(data = null, message = null)
    class Success<T>(data: T) : NetworkResource<T>(data, message = null)
    class Error<T>(errorMessage: String) : NetworkResource<T>(data = null, errorMessage)
}
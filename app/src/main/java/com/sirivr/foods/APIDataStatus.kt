package com.sirivr.foods

sealed class APIDataStatus<T>(val data: T? = null, val message: String? = null) {
    class SUCCESS<T>(data: T?) : APIDataStatus<T>(data)
    class ERROR<T>(data: T? = null, message: String? = null) : APIDataStatus<T>(data, message)
    class LOADING<T>(data: T? = null) : APIDataStatus<T>(data)
}
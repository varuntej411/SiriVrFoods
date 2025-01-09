package com.sirivr.foods.repositoryImpl

import com.sirivr.foods.di.ApiServices
import com.sirivr.foods.model.LoginResponse
import com.sirivr.foods.repository.BaseRepository

class LoginRepositoryImpl(private val apiServices: ApiServices) : BaseRepository {

    override suspend fun getLoginUser(): Result<LoginResponse> {
        return try {
            val response = apiServices.getLoginUser()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: run {
                    Result.failure(Exception("Error Occurred"))
                }
            } else {
                Result.failure(Exception("Error Occurred"))
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
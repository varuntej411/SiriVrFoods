package com.sirivr.foods.repository

import com.sirivr.foods.model.LoginResponse

interface BaseRepository {

    suspend fun getLoginUser(): Result<LoginResponse>
}
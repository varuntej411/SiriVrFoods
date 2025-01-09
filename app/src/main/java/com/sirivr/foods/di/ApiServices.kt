package com.sirivr.foods.di

import com.sirivr.foods.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET
    fun getLoginUser(): Response<LoginResponse>
}

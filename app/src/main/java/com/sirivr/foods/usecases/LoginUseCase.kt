package com.sirivr.foods.usecases

import com.sirivr.foods.APIDataStatus
import com.sirivr.foods.model.LoginResponse
import com.sirivr.foods.repository.BaseRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject


class LoginUseCase @Inject constructor(val repository: BaseRepository) {

    operator fun invoke():Flow<APIDataStatus<LoginResponse>> = flow{
        try {
            emit(APIDataStatus.LOADING())
            delay(3000L)
            val response = repository.getLoginUser()
            if (response.isSuccess) {
                emit(APIDataStatus.SUCCESS(data = response.getOrThrow()))
            } else {
                emit(APIDataStatus.ERROR(message = response.exceptionOrNull()?.localizedMessage))
            }
        }catch (httpException: HttpException){
            emit(APIDataStatus.ERROR(message = httpException.localizedMessage ?: "An Unexpected Error Occurred"))
        } catch (exception: Exception) {
            emit(APIDataStatus.ERROR(message = exception.message ?: "Couldn't reach Server, Please Check Internet Connection"))
        }
    }.flowOn(IO)
}
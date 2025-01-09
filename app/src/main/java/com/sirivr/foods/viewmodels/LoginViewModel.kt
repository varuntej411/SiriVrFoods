package com.sirivr.foods.viewmodels

import androidx.lifecycle.ViewModel
import com.sirivr.foods.APIDataStatus
import com.sirivr.foods.UiText
import com.sirivr.foods.model.LoginResponse
import com.sirivr.foods.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState.UiState())
    val uiState: StateFlow<LoginState.UiState> = _uiState.asStateFlow()

    init {
        getLoginUser()
    }

    private fun getLoginUser() = loginUseCase.invoke().onEach { result ->

        when (result) {
            is APIDataStatus.SUCCESS -> {
                _uiState.update {
                    LoginState.UiState(loginResponse = result.data)
                }
            }

            is APIDataStatus.ERROR -> {
            }

            is APIDataStatus.LOADING -> {
                _uiState.update {
                    LoginState.UiState(isLoading = true)
                }
            }
        }

    }
}

object LoginState {

    data class UiState(
        val isLoading: Boolean = false,
        val loginResponse: LoginResponse? = null,
        val error: UiText = UiText.Idle
    )

    sealed class Navigation() {
        data object Login: Navigation()
    }

    sealed interface LoginEvent {
        data class GetLogin(val loginResponse: LoginResponse): LoginEvent
    }
}

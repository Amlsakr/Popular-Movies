package com.example.popularmovies.common.state

import androidx.annotation.StringRes

sealed class UiState<T>{
    class Init<T> :UiState<T>()

    class Loading<T> :UiState<T>()

    data class Success<T>(val data :T) :UiState<T>()

    data class Error<T>(val message:String?) : UiState<T>()

    data class SoftError<T>(val message: String?) : UiState<T>()

    class Empty<T>(@StringRes val msgResId: Int = 0) : UiState<T>()

    class LoadMore<T> : UiState<T>()
}

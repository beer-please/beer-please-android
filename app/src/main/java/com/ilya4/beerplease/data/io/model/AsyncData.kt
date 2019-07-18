package com.ilya4.beerplease.data.io.model

import com.ilya4.beerplease.domain.entity.RestError

interface AsyncData<T> {
    fun onSuccess(data : T)
    fun onError(restError: RestError)
    fun onFailure(t: Throwable)
}
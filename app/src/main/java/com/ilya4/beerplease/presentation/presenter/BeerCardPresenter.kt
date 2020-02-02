package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.domain.usecase.DefaultObserver
import com.ilya4.beerplease.domain.usecase.GetBeerByIdUseCase
import com.ilya4.beerplease.presentation.view.view.BeerCardMvpView
import moxy.MvpPresenter

class BeerCardPresenter(private val getBeerByIdUseCase: GetBeerByIdUseCase): MvpPresenter<BeerCardMvpView>() {

    fun init(): Boolean {
        getBeerById(-1)
        return false
    }

    private fun getBeerById(id: Int) {
        getBeerByIdUseCase.execute(GetBeerByIdObserver(), GetBeerByIdUseCase.Params.forGetBeerById(id))
    }

    inner class GetBeerByIdObserver: DefaultObserver<GetBeerByIdUseCase.Result>() {
        override fun onNext(result: GetBeerByIdUseCase.Result) {

        }
    }
}
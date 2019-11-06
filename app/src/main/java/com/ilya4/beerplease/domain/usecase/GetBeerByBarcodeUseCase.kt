package com.ilya4.beerplease.domain.usecase

import com.ilya4.beerplease.data.io.model.AsyncData
import com.ilya4.beerplease.domain.entity.Beer
import com.ilya4.beerplease.domain.entity.RestError
import com.ilya4.beerplease.domain.executor.PostExecutionThread
import com.ilya4.beerplease.domain.executor.ThreadExecutor
import com.ilya4.beerplease.presentation.app.RestManager
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetBeerByBarcodeUseCase @Inject constructor(threadExecutor: ThreadExecutor,
                                                  postExecutionThread: PostExecutionThread,
                                                  private val restManager: RestManager
): UseCase<GetBeerByBarcodeUseCase.Result, GetBeerByBarcodeUseCase.Params> (threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Result> {
        return Observable.create { subscriber ->
                val asyncData = object : AsyncData<Beer> {
                    override fun onSuccess(data: Beer) {
                        if (!subscriber.isDisposed) {
                            subscriber.onNext(Result(null, data))
                            subscriber.onComplete()
                        }
                    }

                    override fun onError(restError: RestError) {
                        if (!subscriber.isDisposed) {
                            subscriber.onNext(Result(restError.code, null))
                            subscriber.onComplete()
                        }
                    }

                    override fun onFailure(t: Throwable) {
                        if (!subscriber.isDisposed) {
                            subscriber.onError(t)
                            subscriber.onComplete()
                        }
                    }
                }

            restManager.getBeerByBarcode(params.beerBarcode, asyncData)
        }
    }



    class Params(val beerBarcode: String) {
        companion object {
            fun forGetBeerByBarcode(beerBarcode: String): Params = Params(beerBarcode)
        }
    }

    open class Result(val errorMessage: String?,
                      val beer: Beer?)

}
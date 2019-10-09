package com.ilya4.beerplease.domain.usecase

import com.ilya4.beerplease.data.io.model.AsyncData
import com.ilya4.beerplease.domain.entity.Beer
import com.ilya4.beerplease.domain.entity.RestError
import com.ilya4.beerplease.domain.executor.PostExecutionThread
import com.ilya4.beerplease.domain.executor.ThreadExecutor
import com.ilya4.beerplease.presentation.app.RestManager
import io.reactivex.Observable
import javax.inject.Inject

class GetBeerByIdUseCase @Inject constructor(threadExecutor: ThreadExecutor,
                                             postExecutionThread: PostExecutionThread,
                                             private val restManager: RestManager):
    UseCase<GetBeerByIdUseCase.Result, GetBeerByIdUseCase.Params> (threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Result> {
        return Observable.create { subscriber ->
            val asyncData = object: AsyncData<Beer> {
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
            restManager.getBeerById(params.beerId, asyncData)
        }
    }

    class Params(val beerId: Int) {
        companion object {
            fun forGetBeerById(beerId: Int): Params = Params(beerId)
        }
    }

    open class Result(val errorMessage: String?,
                      val beer: Beer?)
}
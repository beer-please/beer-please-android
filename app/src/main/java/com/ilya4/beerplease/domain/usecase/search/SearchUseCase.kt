package com.ilya4.beerplease.domain.usecase.search

import com.ilya4.beerplease.data.io.model.AsyncData
import com.ilya4.beerplease.domain.entity.Beer
import com.ilya4.beerplease.domain.entity.RestError
import com.ilya4.beerplease.domain.executor.PostExecutionThread
import com.ilya4.beerplease.domain.executor.ThreadExecutor
import com.ilya4.beerplease.domain.usecase.UseCase
import com.ilya4.beerplease.presentation.app.RestManager
import io.reactivex.Observable
import javax.inject.Inject

class SearchUseCase @Inject constructor(threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread,
                                        private val restManager: RestManager):
    UseCase<SearchUseCase.Result, SearchUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Result> {
        return Observable.create {subscriber ->
            val asyncData = object: AsyncData<List<Beer>> {
                override fun onSuccess(data: List<Beer>) {
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
            restManager.searchBeers(params.query, params.page, params.sizePage, asyncData)
        }
    }

    class Params(val query: String, val page: Int, val sizePage: Int) {
        companion object {
            fun forSearchBeers(query: String, page: Int, sizePage: Int) : Params = Params(query, page, sizePage)
            fun forSearchBeers(query: String): Params = Params(query, 1, 10)
        }
    }

    open class Result(val errorMessage: String?,
                      val searchResult: List<Beer>?)
}
package com.ilya4.beerplease.domain.usecase.search

import com.ilya4.beerplease.data.io.model.AsyncData
import com.ilya4.beerplease.domain.entity.RestError
import com.ilya4.beerplease.domain.entity.search.SearchItem
import com.ilya4.beerplease.domain.executor.PostExecutionThread
import com.ilya4.beerplease.domain.executor.ThreadExecutor
import com.ilya4.beerplease.domain.usecase.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class SearchUseCase @Inject constructor(threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
    UseCase<SearchUseCase.Result, SearchUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Result> {
        return Observable.create {subscriber ->
            val asyncData = object: AsyncData<List<SearchItem>> {
                override fun onSuccess(data: List<SearchItem>) {
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
            getMockSearchResult(params.query, asyncData)
        }
    }

    private fun getMockSearchResult(query: String, asyncData: AsyncData<List<SearchItem>>){
        val list = SearchItem.getListSearchItems()
        asyncData.onSuccess(list.filter { it.name.contains(query, true) })
    }

    class Params(val query: String) {
        companion object {
            fun forSearchBeers(query: String) : Params = Params(query)
        }
    }

    open class Result(val errorMessage: String?,
                      val searchResult: List<SearchItem>?)
}
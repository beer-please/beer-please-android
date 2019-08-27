package com.ilya4.beerplease.presentation.presenter

import android.widget.EditText
import com.ilya4.beerplease.domain.usecase.DefaultObserver
import com.ilya4.beerplease.domain.usecase.search.SearchUseCase
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.component.EditTextDebounce
import com.ilya4.beerplease.presentation.view.view.FSearchMvpView
import io.reactivex.processors.BehaviorProcessor

class FSearchPresenter(view: FSearchMvpView,
                       behaviorProcessor: BehaviorProcessor<Boolean>,
                       private val searchUseCase: SearchUseCase):
    BasePresenter<FSearchMvpView>(view, behaviorProcessor) {

    private lateinit var searchDebounce: EditTextDebounce
    private lateinit var query: String

    override fun init(): Boolean {
        return true
    }

    override fun bindEvents(activity: BaseActivity) {
    }

    override fun stop() {
        super.stop()
        searchUseCase.dispose()
    }

    fun setDebounce(searchEt: EditText) {
        searchDebounce = EditTextDebounce.create(searchEt)
        searchDebounce.watch(
            {
                if (it.trim().isNotEmpty())
                    view.showSearchClearBtn(true)
                else {
                    view.showSearchClearBtn(false)
                    view.showResultNotFound(false)
                }
                if (it.trim().length > 2) {
                getSearchResults(it)
            } else {
                view.clearSearchResults()
            }
        }, 400)
    }

    private fun getSearchResults(query: String) {
        this.query = query
        searchUseCase.execute(SearchObserver(), SearchUseCase.Params.forSearchBeers(query))
    }


    inner class SearchObserver: DefaultObserver<SearchUseCase.Result>() {
        override fun onNext(result: SearchUseCase.Result) {
            if (result.searchResult != null && result.searchResult.isNotEmpty()) {
                view.updateSearchResults(result.searchResult)
                view.showResultNotFound(false)
            } else {
                view.clearSearchResults()
                view.showResultNotFound(true)
                view.setBeerForResultNotFound(query)
            }
        }
    }
}
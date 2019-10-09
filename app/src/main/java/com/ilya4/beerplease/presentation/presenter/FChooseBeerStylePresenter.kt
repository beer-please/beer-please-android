package com.ilya4.beerplease.presentation.presenter

import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.component.EditTextDebounce
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import com.ilya4.beerplease.utils.Utils
import io.reactivex.processors.BehaviorProcessor

class FChooseBeerStylePresenter(view: FChooseBeerStyleMvpView,
                                behaviorProcessor: BehaviorProcessor<Boolean>,
                                val gson: Gson)
    : BasePresenter<FChooseBeerStyleMvpView>(view, behaviorProcessor) {

    private lateinit var searchDebounce: EditTextDebounce
    private lateinit var stylesList: ArrayList<String>

    override fun init(): Boolean {
        setupBeerStylesListFromJson()
        return true
    }

    override fun bindEvents(activity: BaseActivity) {

    }

    fun setSearchDebounce(searchEt: EditText) {
        searchDebounce = EditTextDebounce.create(searchEt)
        searchDebounce.watch { query ->
            if (query.trim().isNotEmpty()) {
                view.showSearchClearBtn(true)
                view.updateStyleList(stylesList.filter { it.contains(query, true) })
            } else {
                view.showSearchClearBtn(false)
                view.updateStyleList(stylesList)
            }
        }
    }

    fun requestToClearSearchInput() {
        view.updateStyleList(stylesList)
    }

    private fun setupBeerStylesListFromJson() {
        val json = Utils.loadJsonStringFromAsset(activity, "beer_styles.json")
        json.let {
            stylesList = gson.fromJson(it, TypeToken.getParameterized(List::class.java, String::class.java).type)
            view.updateStyleList(stylesList)
        }
    }
}
package com.ilya4.beerplease.presentation.presenter

import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.component.EditTextDebounce
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import com.ilya4.beerplease.utils.Utils
import io.reactivex.processors.BehaviorProcessor
import moxy.InjectViewState
import moxy.MvpPresenter
@InjectViewState
class FChooseBeerStylePresenter(val gson: Gson) : MvpPresenter<FChooseBeerStyleMvpView>() {

    private lateinit var searchDebounce: EditTextDebounce
    private lateinit var stylesList: ArrayList<String>

    fun init(): Boolean {
        setupBeerStylesListFromJson()
        return true
    }

    fun setSearchDebounce(searchEt: EditText) {
        searchDebounce = EditTextDebounce.create(searchEt)
        searchDebounce.watch { query ->
            if (query.trim().isNotEmpty()) {
                viewState.showSearchClearBtn(true)
                viewState.updateStyleList(stylesList.filter { it.contains(query, true) })
            } else {
                viewState.showSearchClearBtn(false)
                viewState.updateStyleList(stylesList)
            }
        }
    }

    fun requestToClearSearchInput() {
        viewState.updateStyleList(stylesList)
    }

    private fun setupBeerStylesListFromJson() {
//        val json = Utils.loadJsonStringFromAsset(activity, "beer_styles.json")
//        json.let {
//            stylesList = gson.fromJson(it, TypeToken.getParameterized(List::class.java, String::class.java).type)
//            view.updateStyleList(stylesList)
//        }
    }
}

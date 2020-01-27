package com.ilya4.beerplease.presentation.presenter

import android.content.Context
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.component.EditTextDebounce
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import com.ilya4.beerplease.utils.Utils
import io.reactivex.processors.BehaviorProcessor
import moxy.InjectViewState
import moxy.MvpPresenter
@InjectViewState
class FChooseBeerStylePresenter(val gson: Gson, private val tempRepository: AddBeerTempRepository) : MvpPresenter<FChooseBeerStyleMvpView>() {

    private lateinit var searchDebounce: EditTextDebounce
    private lateinit var stylesList: ArrayList<String>

    fun init(context: Context): Boolean {
        setupBeerStylesListFromJson(context)
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

    fun chooseBeerStyle(style: String) {
        tempRepository.styleBeer = style
        viewState?.closeFragment()
    }

    private fun setupBeerStylesListFromJson(context: Context) {
        val json = Utils.loadJsonStringFromAsset(context, "beer_styles.json")
        json.let {
           stylesList = gson.fromJson(it, TypeToken.getParameterized(List::class.java, String::class.java).type)
            viewState?.updateStyleList(stylesList)
        }
    }
}

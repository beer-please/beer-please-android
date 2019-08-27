package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.domain.usecase.search.SearchUseCase
import com.ilya4.beerplease.presentation.presenter.FSearchPresenter
import com.ilya4.beerplease.presentation.view.view.FSearchMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor


@Module
class SearchModule {

    @Provides
    fun provideFSearchPresenter(view: FSearchMvpView,
                                behaviorProcessor: BehaviorProcessor<Boolean>,
                                searchUseCase: SearchUseCase,
                                settingsDataSource: SettingsDataSource): FSearchPresenter {
        return FSearchPresenter(view, behaviorProcessor, searchUseCase, settingsDataSource)
    }
}
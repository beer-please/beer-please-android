package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.domain.usecase.search.SearchUseCase
import com.ilya4.beerplease.presentation.presenter.FSearchPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun provideFSearchPresenter(searchUseCase: SearchUseCase,
                                settingsDataSource: SettingsDataSource): FSearchPresenter {
        return FSearchPresenter(searchUseCase, settingsDataSource)
    }
}
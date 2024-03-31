package com.niksah.gagarin.ui.main

import com.niksah.gagarin.data.ApiRepository
import com.niksah.gagarin.utils.base.BaseViewModel

class MainViewModel (
    apiRepository: ApiRepository
): BaseViewModel<MainState, MainEvent>(init()) {

}
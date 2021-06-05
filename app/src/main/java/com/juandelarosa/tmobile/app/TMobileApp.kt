package com.juandelarosa.tmobile.app

import android.app.Application
import com.juandelarosa.domain.repositories.TMobileRepository
import com.juandelarosa.domain.usercases.GetHomeFeedsUserCase

class TMobileApp: Application() {
    private  val repository: TMobileRepository
        get() = ServiceLocator.provideRepository()

    val getHomeFeeds: GetHomeFeedsUserCase
        get() = GetHomeFeedsUserCase(repository)
}
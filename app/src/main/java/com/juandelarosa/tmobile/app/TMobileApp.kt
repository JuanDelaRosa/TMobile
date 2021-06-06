package com.juandelarosa.tmobile.app

import android.app.Application
import android.content.Context
import com.juandelarosa.domain.repositories.TMobileRepository
import com.juandelarosa.domain.usercases.GetHomeFeedsUserCase
import com.juandelarosa.domain.usercases.LoadBackupUserCase
import com.juandelarosa.domain.usercases.SaveBackupUserCase

//User cases that are available in this application
class TMobileApp: Application() {

    var context :Context = this

    private  val repository: TMobileRepository
        get() = ServiceLocator.provideRepository(context)

    val getHomeFeeds: GetHomeFeedsUserCase
        get() = GetHomeFeedsUserCase(repository)

    val saveBackup: SaveBackupUserCase
        get() = SaveBackupUserCase(repository)

    val loadBackup: LoadBackupUserCase
        get() = LoadBackupUserCase(repository)
}
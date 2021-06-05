package com.juandelarosa.tmobile.app

import android.app.Application
import android.content.Context
import com.juandelarosa.domain.repositories.TMobileRepository
import com.juandelarosa.domain.usercases.GetHomeFeedsUserCase
import com.juandelarosa.domain.usercases.LoadBackupUserCase
import com.juandelarosa.domain.usercases.SaveBackupUserCase

class TMobileApp(): Application() {
    private  val repository: TMobileRepository
        get() = ServiceLocator.provideRepository(this)

    val getHomeFeeds: GetHomeFeedsUserCase
        get() = GetHomeFeedsUserCase(repository)

    val saveBackup: SaveBackupUserCase
        get() = SaveBackupUserCase(repository)

    val loadBackup: LoadBackupUserCase
        get() = LoadBackupUserCase(repository)
}
package com.juandelarosa.domain.usercases

import com.juandelarosa.domain.repositories.TMobileRepository

///User case to load the backup from the database
class LoadBackupUserCase(private val repository: TMobileRepository) {
    suspend operator fun invoke() = repository.GetBackup()
}
package com.juandelarosa.domain.usercases

import com.juandelarosa.domain.entities.Cards
import com.juandelarosa.domain.entities.CardsDB
import com.juandelarosa.domain.repositories.TMobileRepository

class LoadBackupUserCase(private val repository: TMobileRepository) {
    suspend operator fun invoke() = repository.GetBackup()
}
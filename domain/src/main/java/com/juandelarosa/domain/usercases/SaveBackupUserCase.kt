package com.juandelarosa.domain.usercases

import com.juandelarosa.domain.entities.Cards
import com.juandelarosa.domain.entities.CardsDB
import com.juandelarosa.domain.repositories.TMobileRepository

class SaveBackupUserCase(private val repository: TMobileRepository) {
    suspend operator fun invoke(cards : List<Cards>) = repository.SaveBackup(CardsDB(0,cards))
}
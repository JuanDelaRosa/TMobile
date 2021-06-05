package com.juandelarosa.domain.entities

data class CardsHelper(
    var id : Int = 0,
    var cards : List<CardFromDB>
)

data class CardFromDB(
    val title: TextStyle?,
    val description: TextStyle?,
    val image: Image?,
    val type: CardType
)
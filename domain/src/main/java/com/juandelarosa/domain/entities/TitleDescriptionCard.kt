package com.juandelarosa.domain.entities

data class TitleDescriptionCard(
    override val title: TextStyle,
    val description: TextStyle
) : Card
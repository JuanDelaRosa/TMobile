package com.juandelarosa.domain.entities

data class ImageCard(
    override val title: TextStyle,
    val image: Image,
    val description: TextStyle,
) : Card
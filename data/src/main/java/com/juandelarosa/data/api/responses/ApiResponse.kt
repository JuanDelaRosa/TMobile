package com.juandelarosa.data.api.responses

//Set of classes required to deserialize the service response
data class ApiResponse(
    val page: Page?
)

data class Page(
    val cards: List<CardsApi>?
)

data class CardsApi(
    val card: CardApi?,
    val card_type: String?
)

data class CardApi(
    val attributes: AttributesApi?,
    val description: StyleApi?,
    val image: ImageApi?,
    val title: StyleApi?,
    val value: String?
)

data class AttributesApi(
    val font: FontApi?,
    val text_color: String?
)
data class ImageApi(
    val size: SizeApi?,
    val url: String?
)

data class StyleApi(
    val attributes: AttributesApi?,
    val value: String?
)

data class FontApi(
    val size: Float?
)
data class SizeApi(
    val height: Int?,
    val width: Int?
)
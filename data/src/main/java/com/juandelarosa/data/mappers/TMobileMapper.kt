package com.juandelarosa.data.mappers

import com.google.gson.Gson
import com.juandelarosa.data.api.responses.ApiResponse
import com.juandelarosa.data.api.responses.CardApi
import com.juandelarosa.data.api.responses.StyleApi
import com.juandelarosa.domain.entities.*

//Class dedicated to mapping the response of the server in clean information, free of nulls and ready to use
class TMobileMapper {

    fun testToFeeds(string : String) :ApiResponse =
        Gson().fromJson(string, ApiResponse::class.java)


    fun toFeeds(response: ApiResponse?) : List<Cards> = response?.let { apiR ->
            apiR.page?.let {
                page -> page.cards?.let {
                    cards -> cards.map {
                        getCard(it.card,it.card_type)
                    }
                }
            }
        } ?: emptyList()


    private fun getCard(card : CardApi?, type: String?) : Cards{
        val enumCard = EnumCartType(type)
        val tmpCard = when(enumCard){
            CardType.Image -> {
                getImageCard(card)
            }
            CardType.Text -> {
                getTextCard(card)
            }
            CardType.TitleDescription -> {
                getTitleDescriptionCard(card)
            }
            CardType.Unknown -> {
                TextCard(TextStyle(0f,"",""))
            }
        }
        return Cards(tmpCard,enumCard)
    }

    private fun getImageCard(card : CardApi?) : ImageCard{
        val title = card?.let { CreateTextStyle(it.title ) } ?: TextStyle(0f,"","")
        val description = card?.let { CreateTextStyle(it.description ) } ?: TextStyle(0f,"","")
        val image = card?.let {
            c -> c.image?.let {
                i -> i.size?.let {
                    s -> Image(s.height?:0, s.width?:0,i.url?:"")
                }
            }
        } ?: Image(0,0,"")

        return ImageCard(title, image, description)
    }

    private fun getTitleDescriptionCard(card : CardApi?) : TitleDescriptionCard{
        val title = card?.let { CreateTextStyle(it.title ) } ?: TextStyle(0f,"","")
        val description = card?.let { CreateTextStyle(it.description ) } ?: TextStyle(0f,"","")
        return TitleDescriptionCard(title, description)
    }

    private fun getTextCard(card : CardApi?) : TextCard{
        val size = card?.let { it.attributes?.let { a -> a.font?.size } } ?: 0f
        val color = card?.let { it.attributes?.text_color } ?: ""
        val value = card?.value ?: ""
        return TextCard(TextStyle(size,color,value))
    }

    private fun CreateTextStyle(styleApi: StyleApi?) :TextStyle? =
        styleApi?.let { t -> t.attributes?.let {
                a -> TextStyle(a.font?.size ?: 0f, a.text_color ?: "", styleApi.value ?: "")
            }
        }

    private fun EnumCartType(type: String?) : CardType =
        when (type) {
            "text" -> CardType.Text
            "title_description" -> CardType.TitleDescription
            "image_title_description" -> CardType.Image
            else -> CardType.Unknown
        }
}
package com.juandelarosa.data.mappers

import com.google.gson.Gson
import com.juandelarosa.data.db.entities.LocalBackup
import com.juandelarosa.domain.entities.*


//Class dedicated to mapping the informacion of the Data Base in clean information, free of nulls and ready to use
class TMobileDBMapper {

    fun toBackup(backup : CardsDB) : LocalBackup =
        LocalBackup(backup.id, Gson().toJson(toCardHelper(backup)))

    fun fromBackup(backup: LocalBackup?) : CardsDB =
        if (backup == null)
            CardsDB(0, emptyList())
        else
            fromCardHelper(Gson().fromJson(backup.info, CardsHelper::class.java))


    private fun toCardHelper(backup : CardsDB) : CardsHelper{
        val list = backup.cards.map {
            toSave(it)
        }
        return CardsHelper(0,list)
    }

    private fun fromCardHelper(helper: CardsHelper) : CardsDB{
        val list = helper.cards.map {
            parseCardFromDB(it)
        }
        return CardsDB(helper.id,list)
    }

    private fun parseCardFromDB(card : CardFromDB) : Cards{
        return when(card.type){
            CardType.Image -> {
                val title = card.title ?: TextStyle(0f,"","")
                val description = card.description ?: TextStyle(0f,"","")
                val image = card.image ?: Image(0,0,"")
                Cards(ImageCard(title,image, description),card.type)
            }
            CardType.TitleDescription -> {
                val title = card.title ?: TextStyle(0f,"","")
                val description = card.description ?: TextStyle(0f,"","")
                Cards(TitleDescriptionCard(title,description),card.type)
            }
            CardType.Text -> {
                val title = card.title ?: TextStyle(0f,"","")
                Cards(TextCard(title),card.type)
            }
            CardType.Unknown -> {
                Cards(TextCard(TextStyle(0f,"","")),card.type)
            }
        }
    }

    private fun toSave(cards: Cards) : CardFromDB{
        return when(cards.card_type){
            CardType.Image -> {
                val tmpCard = cards.card as ImageCard
                CardFromDB(tmpCard.title,tmpCard.description,tmpCard.image,cards.card_type)
            }
            CardType.TitleDescription -> {
                val tmpCard = cards.card as TitleDescriptionCard
                CardFromDB(tmpCard.title,tmpCard.description,null,cards.card_type)
            }
            CardType.Text -> {
                val tmpCard = cards.card as TextCard
                CardFromDB(tmpCard.title,null,null,cards.card_type)
            }
            CardType.Unknown -> {
                CardFromDB(null,null,null,cards.card_type)
            }
        }
    }


    //It is not possible to deserialize an interface (Card) so I created this helper class to be able to perform this process
    private data class CardsHelper(
        var id : Int = 0,
        var cards : List<CardFromDB>
    )

    private data class CardFromDB(
        val title: TextStyle?,
        val description: TextStyle?,
        val image: Image?,
        val type: CardType
    )
}
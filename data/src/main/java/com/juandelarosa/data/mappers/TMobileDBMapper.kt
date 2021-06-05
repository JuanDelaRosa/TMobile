package com.juandelarosa.data.mappers

import com.google.gson.Gson
import com.juandelarosa.data.db.entities.LocalBackup
import com.juandelarosa.domain.entities.*

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
                val title = card.title ?: TextStyle(0,"","")
                val description = card.description ?: TextStyle(0,"","")
                val image = card.image ?: Image(0,0,"")
                Cards(ImageCard(title,image, description),card.type)
            }
            CardType.TitleDescription -> {
                val title = card.title ?: TextStyle(0,"","")
                val description = card.description ?: TextStyle(0,"","")
                Cards(TitleDescriptionCard(title,description),card.type)
            }
            CardType.Text -> {
                val title = card.title ?: TextStyle(0,"","")
                Cards(TextCard(title),card.type)
            }
            CardType.Unknown -> {
                Cards(TextCard(TextStyle(0,"","")),card.type)
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
}
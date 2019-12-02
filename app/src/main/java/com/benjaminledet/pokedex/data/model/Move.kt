package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benjaminledet.pokedex.data.remote.response.PokemonTypeResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = Move.TABLE_NAME)
data class Move(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,


    @ColumnInfo(name = TYPE)
    val types: String,

    @ColumnInfo(name = ACCUARCY)
    val accuarcy: Int,

    @ColumnInfo(name = POWER)
    val power: Int,

    @ColumnInfo(name = PP)
    val pp: Int
) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "Move"
        const val ID = "id"
        const val NAME = "name"
        const val TYPE = "type"
        const val ACCUARCY = "accuracy"
        const val POWER = "power"
        const val PP = "pp"
    }
}
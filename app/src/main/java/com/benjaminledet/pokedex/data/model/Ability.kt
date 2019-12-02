package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Ability.TABLE_NAME)
data class Ability(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name =DESCRIPTION)
    val description: String

) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "Ability"
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
    }
}
package com.benjaminledet.pokedex.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.benjaminledet.pokedex.data.model.Ability

@Dao
abstract class AbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<Ability>): List<Long>

    @Delete
    abstract suspend fun delete(data: List<Ability>)

    @Query("DELETE FROM ${Ability.TABLE_NAME}")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM ${Ability.TABLE_NAME} WHERE ${Ability.ID} = :id")
    abstract suspend fun getById(id: Int?): Ability?

    @Query("SELECT * FROM ${Ability.TABLE_NAME} WHERE ${Ability.ID} = :id")
    abstract fun getByIdObservable(id: Int?): LiveData<Ability?>

    @Query("SELECT * FROM ${Ability.TABLE_NAME}")
    abstract suspend fun getAll(): List<Ability>

    @Query("SELECT * FROM ${Ability.TABLE_NAME}")
    abstract fun getAllObservable(): LiveData<List<Ability>>

    @Query("SELECT * FROM ${Ability.TABLE_NAME} WHERE ${Ability.NAME} IN(:names)")
    abstract fun getAllObservable(names: List<String>): LiveData<List<Ability>>

    @Query("SELECT * FROM ${Ability.TABLE_NAME}")
    abstract fun getAllPaged(): DataSource.Factory<Int, Ability>
}
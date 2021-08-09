package jamesdeperio.github.itunesdemo.network.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ApiCacheDao {
    @Query("SELECT * FROM apicacheentity WHERE `key` = :key  ")
    fun findCacheByKey(key:String): ApiCacheEntity?

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertCache( apiCacheEntity: ApiCacheEntity)

}
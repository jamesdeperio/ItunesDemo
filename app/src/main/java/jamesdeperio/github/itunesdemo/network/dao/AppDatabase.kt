package jamesdeperio.github.itunesdemo.network.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ApiCacheEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apiCacheDao(): ApiCacheDao
}
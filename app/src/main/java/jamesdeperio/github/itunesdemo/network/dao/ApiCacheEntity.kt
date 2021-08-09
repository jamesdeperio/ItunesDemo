package jamesdeperio.github.itunesdemo.network.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApiCacheEntity (
    @PrimaryKey @ColumnInfo(name = "key") val key: String,
    @ColumnInfo(name = "cache") val cache: String?,
)
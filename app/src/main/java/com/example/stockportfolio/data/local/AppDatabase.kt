package com.example.stockportfolio.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stockportfolio.data.local.enitity.HoldingEntity

@Database(
    entities = [HoldingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun holdingsDao(): HoldingsDao
}

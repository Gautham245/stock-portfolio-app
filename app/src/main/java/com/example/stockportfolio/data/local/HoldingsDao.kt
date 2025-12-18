package com.example.stockportfolio.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stockportfolio.data.local.enitity.HoldingEntity

@Dao
interface HoldingsDao {

    @Query("SELECT * FROM holdings")
    suspend fun getAllHoldings(): List<HoldingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoldings(holdings: List<HoldingEntity>)

    @Query("DELETE FROM holdings")
    suspend fun clearHoldings()
}

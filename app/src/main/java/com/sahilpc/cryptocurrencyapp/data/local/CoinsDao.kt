package com.sahilpc.cryptocurrencyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahilpc.cryptocurrencyapp.data.remote.dto.CoinDto

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(list: List<CoinDto>)

    @Query("SELECT * FROM CoinDto")
    suspend fun getCoinsFromRoom():List<CoinDto>
}
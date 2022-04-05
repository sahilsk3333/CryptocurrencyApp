package com.sahilpc.cryptocurrencyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahilpc.cryptocurrencyapp.data.remote.dto.CoinDto

@Database(entities = [CoinDto::class], version = 1)
abstract class CoinsDatabase:RoomDatabase() {
    abstract val coinsDao : CoinsDao
}
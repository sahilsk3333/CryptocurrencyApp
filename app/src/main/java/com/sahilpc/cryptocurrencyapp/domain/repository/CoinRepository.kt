package com.sahilpc.cryptocurrencyapp.domain.repository

import com.sahilpc.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.sahilpc.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinById(coinId:String): CoinDetailDto
}
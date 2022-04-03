package com.sahilpc.cryptocurrencyapp.data.repository

import com.sahilpc.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.sahilpc.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.sahilpc.cryptocurrencyapp.data.remote.dto.CoinDto
import com.sahilpc.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api:CoinPaprikaApi
    ):CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}
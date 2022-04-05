package com.sahilpc.cryptocurrencyapp.data.repository

import android.app.Application
import com.sahilpc.cryptocurrencyapp.common.utils.NetworkUtils
import com.sahilpc.cryptocurrencyapp.data.local.CoinsDao
import com.sahilpc.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.sahilpc.cryptocurrencyapp.data.remote.dto.*
import com.sahilpc.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val database: CoinsDao,
    private val app: Application
) : CoinRepository {

    init {

    }

    override suspend fun getCoins(): List<CoinDto> {

        return if (NetworkUtils.isInternetAvailable(app)) {
            val coinsList = api.getCoins()
            withContext(Dispatchers.IO){
                database.insertCoins(coinsList)
            }
            coinsList
        } else {
            database.getCoinsFromRoom()
        }

    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}


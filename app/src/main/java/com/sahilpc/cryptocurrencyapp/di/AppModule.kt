package com.sahilpc.cryptocurrencyapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sahilpc.cryptocurrencyapp.common.Constants
import com.sahilpc.cryptocurrencyapp.data.local.CoinsDatabase
import com.sahilpc.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.sahilpc.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.sahilpc.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi,database: CoinsDatabase,app: Application): CoinRepository {
        return CoinRepositoryImpl(api,database.coinsDao,app)
    }

    @Provides
    @Singleton
    fun providesCoinDatabase(app:Application):CoinsDatabase{
        return Room.databaseBuilder(app,CoinsDatabase::class.java,"coin_db")
            .build()
    }
}
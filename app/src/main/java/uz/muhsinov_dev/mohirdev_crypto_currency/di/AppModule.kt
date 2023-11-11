package uz.muhsinov_dev.mohirdev_crypto_currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.muhsinov_dev.mohirdev_crypto_currency.data.ConverterApi
import uz.muhsinov_dev.mohirdev_crypto_currency.repository.MainRepository
import uz.muhsinov_dev.mohirdev_crypto_currency.repository.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getCoinsModule():ConverterApi{
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.coinpaprika.com/v1/")
            .build()
            .create(ConverterApi::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(api: ConverterApi):MainRepository = MainRepositoryImpl(api)

}
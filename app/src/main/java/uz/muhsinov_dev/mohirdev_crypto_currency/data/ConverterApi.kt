package uz.muhsinov_dev.mohirdev_crypto_currency.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.Coin
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.CoinInfo
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Constants

import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Constants.coinId

interface ConverterApi {
    @GET("coins")
  suspend fun getCoinsApi():Response<ArrayList<Coin>>

  @GET("coins/{$coinId}")
  suspend fun getCoinsInfo(@Path(Constants.coinId) coinId:String):Response<CoinInfo>

}
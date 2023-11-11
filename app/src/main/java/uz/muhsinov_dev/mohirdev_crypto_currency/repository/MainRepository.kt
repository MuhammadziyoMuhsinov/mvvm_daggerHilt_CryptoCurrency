package uz.muhsinov_dev.mohirdev_crypto_currency.repository


import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.Coin
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.CoinInfo
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Resource

interface MainRepository {
   suspend fun getCoinsRepository(): Resource<ArrayList<Coin>>
   suspend fun getCoinInfo(coinId:String): Resource<CoinInfo>
}
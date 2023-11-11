package uz.muhsinov_dev.mohirdev_crypto_currency.repository


import uz.muhsinov_dev.mohirdev_crypto_currency.data.ConverterApi
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.Coin
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.CoinInfo
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    val api:ConverterApi
) :MainRepository {
    override suspend fun getCoinsRepository(): Resource<ArrayList<Coin>> {
        return try {
         val response = api.getCoinsApi()
            if (response.isSuccessful&&response.body() !=null){
                Resource.Success(response.body()!!)
            }else{
                Resource.Failure(response.message().toString())
            }

        }catch (e:Exception){
            Resource.Failure(e.message.toString())
        }
    }

    override suspend fun getCoinInfo(coinId: String): Resource<CoinInfo> {
        return try {
            val response = api.getCoinsInfo(coinId)
            if (response.isSuccessful&&response.body() !=null){
                Resource.Success(response.body()!!)
            }else{
                Resource.Failure(response.message().toString())
            }

        }catch (e:Exception){
            Resource.Failure(e.message.toString())
        }
    }
}
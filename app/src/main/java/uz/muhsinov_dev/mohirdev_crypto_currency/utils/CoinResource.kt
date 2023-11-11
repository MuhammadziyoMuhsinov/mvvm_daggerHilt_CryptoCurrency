package uz.muhsinov_dev.mohirdev_crypto_currency.utils



sealed class CoinResource() {

    data class Success<T>(val data:T?):CoinResource()
    data class Failure(val message:String):CoinResource()

    object Loading : CoinResource()
    object Empty : CoinResource()
}
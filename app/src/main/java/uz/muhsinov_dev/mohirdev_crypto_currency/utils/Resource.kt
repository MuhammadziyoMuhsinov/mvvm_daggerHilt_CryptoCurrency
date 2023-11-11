package uz.muhsinov_dev.mohirdev_crypto_currency.utils

sealed class Resource<T>(data:T?,message:String?=null) {
    data class Success<T>(val data: T):Resource<T>(data)
    data class Failure<T>(val message:String):Resource<T>(null,message = message)

}
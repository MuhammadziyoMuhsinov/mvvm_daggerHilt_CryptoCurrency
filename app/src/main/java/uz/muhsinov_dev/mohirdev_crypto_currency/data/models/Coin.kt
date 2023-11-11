package uz.muhsinov_dev.mohirdev_crypto_currency.data.models

data class Coin(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
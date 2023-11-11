package uz.muhsinov_dev.mohirdev_crypto_currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.muhsinov_dev.mohirdev_crypto_currency.repository.MainRepository
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.CoinResource
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Resource
import javax.inject.Inject

@HiltViewModel
class GetCoinViewModel @Inject constructor(
   private val mainRepository: MainRepository
) : ViewModel() {

    private val call = MutableStateFlow<CoinResource>(CoinResource.Empty)

    val answer:StateFlow<CoinResource> get() = call

    fun getCoinsList(){
        viewModelScope.launch {
            call.value = CoinResource.Loading
         when(val response =  mainRepository.getCoinsRepository()) {
             is Resource.Failure -> {
                 call.value = CoinResource.Failure(response.message)
             }
             is Resource.Success -> {
                 call.value = CoinResource.Success(response.data)
             }
         }

        }
    }

}
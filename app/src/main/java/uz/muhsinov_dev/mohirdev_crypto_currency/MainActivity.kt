package uz.muhsinov_dev.mohirdev_crypto_currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.muhsinov_dev.mohirdev_crypto_currency.databinding.ActivityMainBinding
import uz.muhsinov_dev.mohirdev_crypto_currency.viewmodel.GetCoinViewModel
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.CoinResource

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
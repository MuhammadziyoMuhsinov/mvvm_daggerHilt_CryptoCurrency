package uz.muhsinov_dev.mohirdev_crypto_currency

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout.LayoutParams
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.CoinInfo
import uz.muhsinov_dev.mohirdev_crypto_currency.databinding.FragmentCoinInfoBinding
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.CoinResource
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.Constants
import uz.muhsinov_dev.mohirdev_crypto_currency.viewmodel.GetCoinInfoViewModel
import uz.muhsinov_dev.mohirdev_crypto_currency.viewmodel.GetCoinViewModel

@AndroidEntryPoint
class CoinInfoFragment : Fragment() {
    private var _binding: FragmentCoinInfoBinding? = null
    private val binding get() = _binding!!

    private val coinInfoViewModel:GetCoinInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinInfoViewModel.getCoinInfo(arguments?.getString(Constants.coinId) as String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCoinInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            coinInfoViewModel.answer.collectLatest {
                when(it){
                    CoinResource.Empty -> {}
                    is CoinResource.Failure -> {
                        binding.apply {
                            progressBar.visibility = View.INVISIBLE
                            tvError.visibility = View.VISIBLE
                            coinName.visibility = View.INVISIBLE
                            tvError.text = it.message
                        }

                    }
                    CoinResource.Loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            tvError.visibility = View.INVISIBLE
                            container.visibility = View.INVISIBLE
                        }
                    }
                    is CoinResource.Success<*> -> {
                        binding.apply {
                            progressBar.visibility = View.INVISIBLE
                            tvError.visibility = View.INVISIBLE
                            container.visibility = View.VISIBLE
                        }
                        populateData(it.data as CoinInfo)
                    }
                }
            }
        }


    }

    private fun populateData(coinInfo:CoinInfo){
        Glide.with(requireContext()).load(coinInfo.logo).into(binding.logo)
        binding.coinName.text = coinInfo.rank.toString() + " "+ coinInfo.name
        binding.coinStatus.text = if (true) "Active" else "Not Active"
        binding.coinStatus.setTextColor(if (true) Color.GREEN else Color.RED)
        binding.description.text = coinInfo.description
        if (!coinInfo.tags.isNullOrEmpty()){
            coinInfo.tags.forEach{
                binding.flowLayout.addView(createTextView(it.name))
            }
        }
    }

    fun createTextView(tagtext:String):TextView{
        val tv = TextView(requireContext())
        tv.setText(tagtext)
        tv.setTextColor(Color.GREEN)
        tv.setBackgroundResource(R.drawable.border_tv)
        tv.layoutParams = ViewGroup.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        tv.setPadding(30,20,30,20)
        return tv
    }


}
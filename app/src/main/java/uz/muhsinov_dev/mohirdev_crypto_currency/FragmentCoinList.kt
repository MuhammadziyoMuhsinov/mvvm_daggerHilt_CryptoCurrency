package uz.muhsinov_dev.mohirdev_crypto_currency

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.muhsinov_dev.mohirdev_crypto_currency.adapter.RvAdapter
import uz.muhsinov_dev.mohirdev_crypto_currency.data.models.Coin
import uz.muhsinov_dev.mohirdev_crypto_currency.databinding.FragmentCoinListBinding
import uz.muhsinov_dev.mohirdev_crypto_currency.utils.CoinResource
import uz.muhsinov_dev.mohirdev_crypto_currency.viewmodel.GetCoinViewModel


@AndroidEntryPoint
class FragmentCoinList : Fragment() {

    val viewModel: GetCoinViewModel by viewModels()
    val TAG = "TAG"
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCoinsList()
        rvAdapter = RvAdapter(ArrayList())

    }

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCoinListBinding.inflate(layoutInflater)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.rv.adapter = rvAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.answer.collectLatest {
                when (it) {
                    CoinResource.Empty -> {
                        Unit
                    }

                    is CoinResource.Failure -> {

                        binding!!.apply {
                            progressBar.visibility = View.INVISIBLE
                            tvError.visibility = View.VISIBLE
                            rv.visibility = View.INVISIBLE
                            tvError.text = it.message
                        }
                    }

                    CoinResource.Loading -> {
                        binding!!.apply {
                            progressBar.visibility = View.VISIBLE
                            tvError.visibility = View.INVISIBLE
                            rv.visibility = View.INVISIBLE
                        }

                    }

                    is CoinResource.Success<*> -> {
                        binding!!.apply {
                            progressBar.visibility = View.INVISIBLE
                            tvError.visibility = View.INVISIBLE
                            rv.visibility = View.VISIBLE
                        }
                        rvAdapter.list = it.data as ArrayList<Coin>
                        rvAdapter.notifyDataSetChanged()
                    }
                }

            }
        }
    }


}
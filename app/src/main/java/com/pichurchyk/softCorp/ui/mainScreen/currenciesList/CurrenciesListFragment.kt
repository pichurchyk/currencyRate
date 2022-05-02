package com.pichurchyk.softCorp.ui.mainScreen.currenciesList

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pichurchyk.data.dto.CurrencyRateItemDto
import com.pichurchyk.softCorp.R
import com.pichurchyk.softCorp.common.base.BaseFragment
import com.pichurchyk.softCorp.common.constants.SortConstants
import com.pichurchyk.softCorp.common.delegate.viewBinding
import com.pichurchyk.softCorp.databinding.FragmentCurrenciesListBinding
import com.pichurchyk.softCorp.ui.mainScreen.adapter.BaseCodesAdapter
import com.pichurchyk.softCorp.ui.mainScreen.adapter.CurrenciesListAdapter
import com.pichurchyk.softCorp.ui.mainScreen.adapter.SortAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesListFragment : BaseFragment(R.layout.fragment_currencies_list),
    CurrenciesListAdapter.ItemClick, BaseCodesAdapter.ItemClick, SortAdapter.ItemClick {
    private val binding by viewBinding<FragmentCurrenciesListBinding>()
    private val viewModel by viewModels<CurrenciesListViewModel>()

    private var currencyListAdapter: CurrenciesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObservers()
        setupBaseCurrencySpinner()
        setupSortBySpinner()
        setupListeners()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.currencies.collect {
                currencyListAdapter?.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.error.collect { errorText ->
                if (errorText != null) {
                    snackbar(binding.root, errorText)
                }
            }
        }
    }

    private fun setupListeners() {
        binding.spinnerBaseCurrency.onItemSelectedListener = BaseCodesAdapter(this)
        binding.spinnerSortBy.onItemSelectedListener = SortAdapter(this)
    }

    private fun setupAdapter() {
        currencyListAdapter = CurrenciesListAdapter(this)
        binding.list.apply {
            val linearLayoutManager = LinearLayoutManager(binding.list.context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = currencyListAdapter
            layoutManager = linearLayoutManager
            val dividerItemDecoration =
                DividerItemDecoration(binding.list.context, linearLayoutManager.orientation)
            dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    binding.list.context,
                    R.drawable.currencies_list_divider
                )!!
            )
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun setupBaseCurrencySpinner() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.baseCodes.collect { baseCodes ->
                val baseCodesArrayAdapter = ArrayAdapter(
                    binding.root.context,
                    R.layout.spinner_item,
                    baseCodes
                ).apply {
                    setDropDownViewResource(R.layout.spinner_item)
                }
                binding.spinnerBaseCurrency.adapter = baseCodesArrayAdapter
            }
        }
        viewModel.getAllBaseCurrencies()
    }

    private fun setupSortBySpinner() {
        val sortConstantsArray = mutableListOf(
            SortConstants.AZ.name,
            SortConstants.ZA.name,
            SortConstants.LOWEST.name,
            SortConstants.HIGHEST.name
        )
        val sortSpinnerAdapter = ArrayAdapter(
            binding.root.context,
            R.layout.spinner_item,
            sortConstantsArray
        ).apply {
            setDropDownViewResource(R.layout.spinner_item)
        }
        binding.spinnerSortBy.adapter = sortSpinnerAdapter

    }

    override fun toggleFavoriteCurrency(currencyRateItem: CurrencyRateItemDto) {
        viewModel.toggleFavoriteCurrency(currencyRateItem)
    }

    override fun changeCode(parentView: AdapterView<*>?, position: Int) {
        viewModel.getCurrenciesList(parentView?.getItemAtPosition(position).toString())
        binding.list.scrollToPosition(0)
    }

    override fun changeSort(parentView: AdapterView<*>?, position: Int) {
        viewModel.sortList(
            SortConstants.valueOf(
                parentView?.getItemAtPosition(position).toString()
            )
        )
        binding.list.scrollToPosition(0)
    }
}
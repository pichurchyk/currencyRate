package com.pichurchyk.softCorp.ui.mainScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pichurchyk.data.dto.CurrencyRateItemDto
import com.pichurchyk.softCorp.R
import com.pichurchyk.softCorp.databinding.CurrencyListItemBinding
import com.pichurchyk.softCorp.util.view.changeDrawable

class CurrenciesListAdapter(private val itemClick: ItemClick) :
    ListAdapter<CurrencyRateItemDto, CurrenciesListAdapter.CurrencyViewHolder>(COMPARATOR) {

    inner class CurrencyViewHolder(private val binding: CurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyRateItem: CurrencyRateItemDto) {
            binding.currencyName.text = currencyRateItem.name
            binding.currencyValue.text = currencyRateItem.value.toString()

            if (currencyRateItem.isLiked) {
                binding.btnLike.changeDrawable(R.drawable.ic_like_fill)
            } else {
                binding.btnLike.changeDrawable(R.drawable.ic_like)
            }

            binding.btnLike.setOnClickListener {
                itemClick.toggleFavoriteCurrency(currencyRateItem)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CurrencyRateItemDto>() {
            override fun areItemsTheSame(
                oldItem: CurrencyRateItemDto,
                newItem: CurrencyRateItemDto
            ) =
                oldItem.value == newItem.value

            override fun areContentsTheSame(
                oldItem: CurrencyRateItemDto,
                newItem: CurrencyRateItemDto
            ) =
                oldItem == newItem
        }
    }

    interface ItemClick {
        fun toggleFavoriteCurrency(currencyRateItem: CurrencyRateItemDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            CurrencyListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}
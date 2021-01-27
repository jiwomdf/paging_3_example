package com.programmergabut.paging3example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmergabut.paging3example.databinding.ListFooterBinding

class FooterAdapter(
    private val retry: () -> Unit,
    private val showError: (loadState: LoadState) -> Unit
): LoadStateAdapter<FooterAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ListFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: ListFooterBinding): RecyclerView.ViewHolder(binding.root){

        private val LOADING = 1
        private val ERROR = 2
        private val NOTLOADING = 3

        init {
            binding.ivRetry.setOnClickListener {
                setComponentVisibility(LOADING)
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            when(loadState) {
                is LoadState.Loading -> {
                    setComponentVisibility(LOADING)
                }
                is LoadState.Error -> {
                    showError.invoke(loadState)
                    setComponentVisibility(ERROR)
                }
                is LoadState.NotLoading -> {
                    setComponentVisibility(NOTLOADING)
                }
            }
        }

        private fun setComponentVisibility(status: Int) {
            when(status){
                LOADING -> {
                    binding.ivRetry.visibility = View.GONE
                    binding.pbFooter.visibility = View.VISIBLE
                }
                ERROR -> {
                    binding.ivRetry.visibility = View.VISIBLE
                    binding.pbFooter.visibility = View.GONE
                }
                NOTLOADING -> {
                    binding.ivRetry.visibility = View.GONE
                    binding.pbFooter.visibility = View.GONE
                }
            }
        }

    }
}
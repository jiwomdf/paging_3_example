package com.programmergabut.paging3example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.programmergabut.paging3example.data.remote.json.Item
import com.programmergabut.paging3example.databinding.ListUsersBinding
import javax.inject.Inject

class UsersAdapter @Inject constructor(
    private val glide: RequestManager
): PagingDataAdapter<Item, UsersAdapter.UsersViewHolder>(USERS_COMPARATOR) {

    companion object {
        private val USERS_COMPARATOR = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = ListUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    inner class UsersViewHolder(private val binding: ListUsersBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Item){
            glide.load(data.avatarUrl).into(binding.ivUser)
            binding.tvUserName.text = data.login
        }
    }

}
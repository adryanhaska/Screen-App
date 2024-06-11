package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemListBinding
import com.example.myapplication.network.User

class UserListAdapter : PagingDataAdapter<User, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: ((firstName: TextView, lastName: TextView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, onItemClickCallback)
        }
    }

    class MyViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User, onItemClickCallback: ((firstName: TextView, lastName: TextView) -> Unit)? = null) {
            binding.tvName.text = data.first_name
            binding.tvLastName.text = data.last_name
            binding.tvEmail.text = data.email
            Glide.with(itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.imgUserAvatar)
            itemView.setOnClickListener {
                onItemClickCallback?.invoke(binding.tvName, binding.tvLastName)
            }
        }
    }

    fun setOnItemClickCallback(callback: (firstName: TextView, lastName: TextView) -> Unit) {
        onItemClickCallback = callback
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

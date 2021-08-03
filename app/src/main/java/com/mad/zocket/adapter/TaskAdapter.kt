package com.mad.zocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mad.zocket.databinding.ItemItemBinding

class TaskAdapter(private val items: List<String>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = items[position]
        holder.mBinding.itemTitle.text = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemBinding: ItemItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mBinding: ItemItemBinding = itemBinding
    }

}
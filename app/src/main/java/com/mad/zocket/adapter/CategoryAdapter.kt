package com.mad.zocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mad.zocket.databinding.ItemItemBinding
import com.mad.zocket.model.Category

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.mBinding.itemTitle.text = category.getName()
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemBinding: ItemItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mBinding: ItemItemBinding = itemBinding
    }

}
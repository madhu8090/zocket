package com.mad.zocket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mad.zocket.R
import com.mad.zocket.databinding.ItemPageBinding
import com.mad.zocket.model.Datum

class PageAdapter(private val datum: List<Datum>, var listener: OnPageManager) :
    RecyclerView.Adapter<PageAdapter.ViewHolder>() {

    companion object {
        var m: OnPageManager? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datum1: Datum = datum[position]
        holder.mBinding.itemPageName.text =
            holder.itemView.context.getString(R.string.page_name    ) + datum1.getName()
        holder.mBinding.itemPageCategory.text =
            holder.itemView.context.getString(R.string.categories) + datum1.getCategory()

        holder.mBinding.root.setOnClickListener {
            listener.onSelected(datum1)
        }
    }

    override fun getItemCount(): Int {
        return datum.size
    }

    inner class ViewHolder(itemBinding: ItemPageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val mBinding: ItemPageBinding = itemBinding
    }

    open interface OnPageManager {
        fun onSelected(datum: Datum)
    }

}
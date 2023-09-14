package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemTableAdmin
import com.example.esemkarestorant.R

class AdpaterListTable(val context: Context,val item:List<ItemTableAdmin>):RecyclerView.Adapter<AdpaterListTable.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var nameTable:TextView=view.findViewById(R.id.lb_nameTable_admin)
        var codeTable:TextView=view.findViewById(R.id.lb_codeTable_Admin)
        var price:TextView=view.findViewById(R.id.lb_price_Admin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdpaterListTable.ViewHolder {
        return ViewHolder(LayoutInflater.from(context.applicationContext).inflate(R.layout.adapter_list_table,parent,false))
    }

    override fun onBindViewHolder(holder: AdpaterListTable.ViewHolder, position: Int) {
        val item=item[position]
        holder.nameTable.text=item.number
        holder.codeTable.text=item.code
        holder.price.text=item.total
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
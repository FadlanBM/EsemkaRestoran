package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemListPesanan
import com.example.esemkarestorant.R

class AdapterListPesananAdmin(val context: Context,val item:List<ItemListPesanan>):RecyclerView.Adapter<AdapterListPesananAdmin.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val namePesanan:TextView=view.findViewById(R.id.tvNamePesanan)
        val pricePesanan:TextView=view.findViewById(R.id.tvpricePesanan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterListPesananAdmin.ViewHolder {
        return ViewHolder(LayoutInflater.from(context.applicationContext).inflate(R.layout.adapter_list_pesanan,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterListPesananAdmin.ViewHolder, position: Int) {
       val items=item[position]
        holder.namePesanan.text=items._Id
        holder.pricePesanan.text=items._Price
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
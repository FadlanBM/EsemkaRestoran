package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemListPesanan
import com.example.esemkarestorant.R

class AdapterListPesananAdmin(val context: Context,val item:List<ItemListPesanan>,val potitions:Int):RecyclerView.Adapter<AdapterListPesananAdmin.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val namePesanan:TextView=view.findViewById(R.id.tvNamePesanan)
        val pricePesanan:TextView=view.findViewById(R.id.tvpricePesanan)
        val count:TextView=view.findViewById(R.id.tvCountNamePesanan)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterListPesananAdmin.ViewHolder {
        return ViewHolder(LayoutInflater.from(context.applicationContext).inflate(R.layout.adapter_list_pesanan,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterListPesananAdmin.ViewHolder, position: Int) {
       val items=item[potitions]
        Log.e("List pesanan admin", item.toString())
        holder.namePesanan.text=items._NameProduk
        holder.pricePesanan.text=items._Price
        holder.count.text=items._Count
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
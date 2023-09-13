package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemListCemilan
import com.example.esemkarestorant.R

class AdapterCemilanMenu(val context: Context,val items:List<ItemListCemilan>):RecyclerView.Adapter<AdapterCemilanMenu.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterlistdata,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=items[position]
        holder.nameproduct.text=item.nama
        holder.priceProduct.text=item.price
        holder.image.setImageBitmap(item.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var nameproduct:TextView=view.findViewById(R.id.lb_nameProduct)
        var priceProduct:TextView=view.findViewById(R.id.lb_priceProduk)
        val image:ImageView=view.findViewById(R.id.imageProduk)
    }
}
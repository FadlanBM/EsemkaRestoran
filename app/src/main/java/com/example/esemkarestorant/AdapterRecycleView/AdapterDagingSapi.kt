package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemListSapi
import com.example.esemkarestorant.R

class AdapterDagingSapi(private val context:Context,private var item:List<ItemListSapi>):RecyclerView.Adapter<AdapterDagingSapi.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var name:TextView=view.findViewById(R.id.lb_nameProduct)
        var price:TextView=view.findViewById(R.id.lb_priceProduk)
        val image:ImageView=view.findViewById(R.id.imageProduk)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDagingSapi.ViewHolder {
        return  ViewHolder(LayoutInflater.from(context.applicationContext).inflate(R.layout.adapterlistdata,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterDagingSapi.ViewHolder, position: Int) {
        var items=item[position]
        holder.name.text=items.nama
        holder.price.text=items.price
        holder.image.setImageBitmap(items.image)
    }

    override fun getItemCount(): Int {
      return item.size
    }
}
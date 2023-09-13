package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemListcart
import com.example.esemkarestorant.R

class AdapterRecyleCart(val context: Context,val items:List<ItemListcart>):RecyclerView.Adapter<AdapterRecyleCart.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRecyleCart.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterlistcart,parent,false))
    }


    override fun onBindViewHolder(holder: AdapterRecyleCart.ViewHolder, position: Int) {
        val item=items[position]
        holder.namePropduk.text=item.name
        holder.priceProduk.text=item.price
        holder.image.setImageBitmap(item.image)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var namePropduk:TextView=view.findViewById(R.id.lb_cart_nameProduk)
        var priceProduk:TextView=view.findViewById(R.id.lb_cart_price)
        var image:ImageView=view.findViewById(R.id.image_cart)
    }

}
package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.DetailActivity
import com.example.esemkarestorant.ItemData.ItemListTable
import com.example.esemkarestorant.R

class AdapterListMenu(private val itemListTable: List<ItemListTable>,private val context: Context, private val fm: FragmentManager):RecyclerView.Adapter<AdapterListMenu.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListMenu.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterlistdata,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterListMenu.ViewHolder, position: Int) {
        var item=itemListTable[position]
        var id=item.id
        holder.nameproduk.text=item.name
        holder.priceProduk.text=item.price
        holder.imageProduk.setImageBitmap(item.image)
        holder.relatifData.setOnClickListener {
            var intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("id",id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemListTable.size
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var nameproduk:TextView=view.findViewById(R.id.lb_nameProduct)
        var priceProduk:TextView=view.findViewById(R.id.lb_priceProduk)
        var imageProduk:ImageView=view.findViewById(R.id.imageProduk)
        var relatifData:RelativeLayout=view.findViewById(R.id.relatifMain)
    }

}
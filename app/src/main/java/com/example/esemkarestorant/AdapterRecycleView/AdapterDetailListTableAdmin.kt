package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemDetailTable
import com.example.esemkarestorant.R
import org.w3c.dom.Text

class AdapterDetailListTableAdmin(val context: Context,val items:List<ItemDetailTable>):RecyclerView.Adapter<AdapterDetailListTableAdmin.Viewholder>() {
    class Viewholder(view:View):RecyclerView.ViewHolder(view) {
        val numberTable:TextView=view.findViewById(R.id.tvnumberTable)
        val tvTanggal:TextView=view.findViewById(R.id.tvTanggalTable)
        val status:TextView=view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDetailListTableAdmin.Viewholder {
      return Viewholder(LayoutInflater.from(context.applicationContext).inflate(R.layout.adapter_detail_table,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterDetailListTableAdmin.Viewholder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
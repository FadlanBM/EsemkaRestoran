package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemDetailTable
import com.example.esemkarestorant.R
import org.w3c.dom.Text

class AdapterDetailListTableAdmin(val context: Context,val items:List<ItemDetailTable>):RecyclerView.Adapter<AdapterDetailListTableAdmin.Viewholder>() {
    class Viewholder(view:View):RecyclerView.ViewHolder(view) {
        val numberTable:TextView=view.findViewById(R.id.tvnumberTable)
        val tvTanggal:TextView=view.findViewById(R.id.tvTanggalTable)
        val status:TextView=view.findViewById(R.id.tvStatus)
        val rvPesananAdmin: RecyclerView = view.findViewById(R.id.recyleViewTotalCart)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDetailListTableAdmin.Viewholder {
      return Viewholder(LayoutInflater.from(context.applicationContext).inflate(R.layout.adapter_detail_table,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterDetailListTableAdmin.Viewholder, position: Int) {
        val item=items[position]
        holder.numberTable.text=item._NoPesanan
        holder.tvTanggal.text=item._DatePemesanan
        holder.status.text=item._Status
        val adapterPesananAdmin = AdapterListPesananAdmin(context, item._ListItemPesanan,position)
        Log.e("listPesananAdapter", item._ListItemPesanan.toString())
        holder.rvPesananAdmin.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        holder.rvPesananAdmin.adapter = adapterPesananAdmin
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
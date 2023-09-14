package com.example.esemkarestorant.AdapterRecycleView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemListcart
import com.example.esemkarestorant.R
import com.example.esemkarestorant.Util.SharePreft_Char

class AdapterRecyleCart(val context: Context,val items:List<ItemListcart>,val lifecycleOwner: LifecycleOwner):RecyclerView.Adapter<AdapterRecyleCart.ViewHolder>() {

    private var result= MutableLiveData<Int>()
    private val readresuld:LiveData<Int> get() = result
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRecyleCart.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterlistcart,parent,false))
    }


    override fun onBindViewHolder(holder: AdapterRecyleCart.ViewHolder, position: Int) {
        val item=items[position]
        result.value=item.count.toInt()
        holder.namePropduk.text=item.name
        holder.priceProduk.text=item.price
        holder.image.setImageBitmap(item.image)
        readresuld.observe(lifecycleOwner){
            item.count=it.toString()
        }
        holder.count.text=item.count
        holder.btnAdd.setOnClickListener {
            var oldCount=item.count
            addCount()
            readresuld.observe(lifecycleOwner){
                item.count=it.toString()
            }
            SharePreft_Char(context).updateDataCount(oldCount,item.count)
            Log.e("oldCount","data ${oldCount}")
            Log.e("oldCount","data ${item.count}")
            holder.count.text=item.count
        }
        holder.btnMinus.setOnClickListener {
            var oldCount=item.count
            minusCount()
            readresuld.observe(lifecycleOwner){
                item.count=it.toString()
            }
            SharePreft_Char(context).updateDataCount(oldCount,item.count)
            holder.count.text=item.count
        }

    }
    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var namePropduk:TextView=view.findViewById(R.id.lb_cart_nameProduk)
        var priceProduk:TextView=view.findViewById(R.id.lb_cart_price)
        var image:ImageView=view.findViewById(R.id.image_cart)
        var count:TextView=view.findViewById(R.id.lb_count_produk)
        val btnAdd:Button=view.findViewById(R.id.btn_cart_plus)
        val btnMinus:Button=view.findViewById(R.id.btn_cart_min)

    }
    fun addCount(){
        result.value=(result.value)?.plus(1)
    }
    fun  minusCount(){
        result.value=(result.value)?.minus(1)
    }

}
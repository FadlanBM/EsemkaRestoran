package com.example.esemkarestorant

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.AdapterRecycleView.AdapterDetailListTableAdmin
import com.example.esemkarestorant.AdapterRecycleView.AdapterListPesananAdmin
import com.example.esemkarestorant.ItemData.ItemDetailTable
import com.example.esemkarestorant.ItemData.ItemListPesanan
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.Util.SharePreft
import com.example.esemkarestorant.databinding.ActivityDetailBinding
import com.example.esemkarestorant.databinding.ActivityDetailTableBinding
import com.example.esemkarestorant.databinding.AdapterDetailTableBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class DetailTableActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailTableBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewListPesanan: RecyclerView
    private lateinit var listPesananAdapter:AdapterListPesananAdmin
    private lateinit var adapterDetailTable: AdapterDetailListTableAdmin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView=binding.recyleView
        adapterDetailTable=AdapterDetailListTableAdmin(this, emptyList())
        recyclerView.adapter=adapterDetailTable
        getListCode(this,binding).execute()
    }

    private inner class getListCode(val context: Context,val binding: ActivityDetailTableBinding):AsyncTask<String,String,String>(){
        override fun doInBackground(vararg p0: String?): String {
            val id =intent.getStringExtra("id")
            val token="bearer ${SharePreft(context).getToken()}"
            var result=""
            var httpURLConnection:HttpURLConnection?=null
            try {
                val url=URL(BASEAPI.BaseApi+"Api/Table/$id")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="GET"
                httpURLConnection.setRequestProperty("authorization",token)
                httpURLConnection.setRequestProperty("Content-Type","application/josn")
                httpURLConnection.setRequestProperty("accept","text/plain")

                var inputStream=httpURLConnection.inputStream
                var inputStreamReader=InputStreamReader(inputStream)
                var data=inputStreamReader.read()

                while (data!=-1){
                    result+=data.toChar()
                    data=inputStreamReader.read()
                }


            }catch (e:Exception){
                Log.e("Error List","Error $e")
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var num=0
            // Parse JSON string to JSONObject
            var datalist= mutableListOf<ItemDetailTable>()
            val jsonObjectDetail = JSONObject(result)
            var ListPesanan= mutableListOf<ItemListPesanan>()
            val noTable=jsonObjectDetail.getString("number")
            val codeTable=jsonObjectDetail.getString("code")
            var orderId = ""
            var datePemesanan = ""
            var status= ""
            binding.tvNoTable.text="Table ${noTable}"
            binding.tvCode.text=codeTable.toString()
            /*Log.e("data 1",jsonObjectDetail.toString())*/
            val order = jsonObjectDetail.getJSONArray("orders")
            for (i in 0 until order.length()) {
                val resultorder = order.getJSONObject(i)
                /*Log.e("data 2",resultorder.toString())*/
                orderId=resultorder.getString("orderId")
                datePemesanan=resultorder.getString("createdAt")
                status=resultorder.getString("status")
                val orderDetails= resultorder.getJSONArray("orderDetails")
                for (j in 0 until orderDetails.length()) {
                    val resultorderDetails= orderDetails.getJSONObject(j)
                    /*Log.e("data 3",resultorderDetails.toString())*/
                    val count=resultorderDetails.getString("quantity")
                    val menu= resultorderDetails.getJSONObject("menu")
                    Log.e("data 3",menu.toString())

                    val name=menu.getString("name")
                    /*val price=resultorderDetails.getString("subTotal")*/
                    val price=menu.getString("price")
                    ListPesanan.add(ItemListPesanan(count,name,price))
                    Log.e("ListPesanan", ListPesanan.toString())

                }
                datalist.add(ItemDetailTable(orderId,num++.toString(),datePemesanan,status,ListPesanan))
            }
            val adapter=AdapterDetailListTableAdmin(context,datalist)
            Log.e("dataList", datalist.toString())
            recyclerView.adapter=adapter
        }
    }
}
package com.example.esemkarestorant

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.ItemData.ItemDetailTable
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.Util.SharePreft
import com.example.esemkarestorant.databinding.ActivityDetailBinding
import com.example.esemkarestorant.databinding.ActivityDetailTableBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class DetailTableActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailTableBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getListCode(this,binding).execute()
    }

    private inner class getListCode(val context: Context,val binding: ActivityDetailTableBinding):AsyncTask<String,String,List<ItemDetailTable>>(){


        override fun doInBackground(vararg params: String?): List<ItemDetailTable> {
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
                // Parse JSON string to JSONObject
                val jsonObjectDetail = JSONObject(result)
                val noTable=jsonObjectDetail.getString("number")
                val codeTable=jsonObjectDetail.getString("code")
                Log.e("data 1",jsonObjectDetail.toString())
                binding.tvCode.setText(codeTable.toString())
                binding.tvNoTable.setText(noTable.toString())
                val order = jsonObjectDetail.getJSONArray("orders")
                    for (i in 0 until order.length()) {
                        val resultorder = order.getJSONObject(i)
                        Log.e("data 2",resultorder.toString())


                        val orderDetails= resultorder.getJSONArray("orderDetails")
                        for (j in 0 until orderDetails.length()) {
                            val resultorderDetails= orderDetails.getJSONObject(j)
                            Log.e("data 3",resultorderDetails.toString())


                            val menu= resultorderDetails.getJSONObject("menu")
                            Log.e("data 4",menu.toString())
                        }
                    }

            }catch (e:Exception){
                Log.e("Error List","Error $e")
            }
            return emptyList()
        }

        override fun onPostExecute(result: List<ItemDetailTable>?) {
            super.onPostExecute(result)

        }
    }
}
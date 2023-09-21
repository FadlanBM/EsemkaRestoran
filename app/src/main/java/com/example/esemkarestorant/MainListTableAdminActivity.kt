package com.example.esemkarestorant

import android.R
import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.AdapterRecycleView.AdpaterListTable
import com.example.esemkarestorant.ItemData.ItemTableAdmin
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.Util.SharePreft
import com.example.esemkarestorant.databinding.ActivityMainListTableAdminBinding
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainListTableAdminActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainListTableAdminBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter:AdpaterListTable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainListTableAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView=binding.ryListTabelAdmin
        itemAdapter=AdpaterListTable(this, emptyList())
        recyclerView.adapter=itemAdapter
        getDataTable(this).execute()

        binding.btnAddTableAdmin.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(com.example.esemkarestorant.R.layout.adapter_modal_addtable)
            val btnOpenTable = dialog.findViewById<View>(com.example.esemkarestorant.R.id.btnOpenTable)
            val btnCancel = dialog.findViewById<View>(com.example.esemkarestorant.R.id.btnCancel)
//            val dataAdapter: ArrayAdapter<String> = ArrayAdapter<Any?>(
//                this,
//                R.layout.simple_spinner_item, providersList
//            )
//
//            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//            checkInProviders.adapter = dataAdapter
            btnOpenTable.setOnClickListener {

                Toast.makeText(this, "Berhasil Open Table!", Toast.LENGTH_SHORT).show()
            }
            btnCancel.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private inner class getDataTable(val context: Context):AsyncTask<String,String,String>(){
        override fun doInBackground(vararg p0: String?): String {
            var result=""
            var token="bearer ${SharePreft.getIntance(context).getToken()}"
            Log.e("Token","token $token")

            var httpURLConnection:HttpURLConnection?=null
            try {
                var url=URL(BASEAPI.BaseApi+"Api/Table")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="GET"
                httpURLConnection.setRequestProperty("Authorization",token)
                httpURLConnection.doInput=true
                httpURLConnection.connect()

                var input=httpURLConnection.inputStream
                var inputStreamReader=InputStreamReader(input)
                var data=inputStreamReader.read()

                while (data!=-1){
                    result+=data.toChar()
                    data=inputStreamReader.read()
                }
            }catch (e:Exception){
                Log.e("Error Http","Error $e")
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var dataList= mutableListOf<ItemTableAdmin>()
            var jsonArray=JSONArray(result)
            for (i in 0 until jsonArray.length()){
                var jsonObject=jsonArray.getJSONObject(i)
                var id=jsonObject.getString("id")
                var number = jsonObject.getString("number")
                var code=jsonObject.getString("code")
                var total=jsonObject.getString("total")
                dataList.add(ItemTableAdmin(id,number,code,total))
            }
            var itemAdapter=AdpaterListTable(context,dataList)
            recyclerView.adapter=itemAdapter

        }
    }
}
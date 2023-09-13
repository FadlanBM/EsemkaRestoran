package com.example.esemkarestorant.FrangmentTabLayout

import android.content.AbstractThreadedSyncAdapter
import android.content.ClipData.Item
import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.AdapterRecycleView.AdapterDagingSapi
import com.example.esemkarestorant.ItemData.ItemListSapi
import com.example.esemkarestorant.R
import com.example.esemkarestorant.Util.BASEAPI
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DagingSapiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DagingSapiFragment : Fragment() {
    private lateinit var context:Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: AdapterDagingSapi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_daging_sapi, container, false)
        context=requireContext()

        recyclerView=view.findViewById(R.id.recyleView_sapi)
        itemAdapter= AdapterDagingSapi(context, emptyList())
        recyclerView.adapter=itemAdapter
        getDataSapi(context).execute()
        return view
    }

    private inner class getDataSapi(private val context: Context):AsyncTask<String,String,List<ItemListSapi>>(){
        override fun doInBackground(vararg params: String?): List<ItemListSapi> {
            var result=""
            var httpURLConnection:HttpURLConnection?=null
            var httpimage:HttpURLConnection?=null
            var dataSapi= mutableListOf<ItemListSapi>()
            try {
                var url=URL(BASEAPI.BaseApiCatrgory+"DagingSapi")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.setRequestProperty("Content-Type","application/json")
                httpURLConnection.setRequestProperty("accept","text/plain")

                var inputStream=httpURLConnection.inputStream
                var inputStreamReader=InputStreamReader(inputStream)
                var data =inputStreamReader.read()
                while (data!=-1){
                    result+=data.toChar()
                    data=inputStreamReader.read()
                }

                var jsonArray=JSONArray(result)
                Log.e("ArrayData",jsonArray.toString())
                for (i in 0 until jsonArray.length()){
                    var jsonObject=jsonArray.getJSONObject(i)
                    var id=jsonObject.getString("id")
                    var name=jsonObject.getString("name")
                    var price=jsonObject.getString("price")
                    var url=URL(BASEAPI.BaseApi+"Api/Menu/$id/Photo")
                    httpimage=url.openConnection() as  HttpURLConnection
                    httpimage.doInput=true
                    httpimage.connect()
                    var input=httpimage.inputStream
                    var bitmap=BitmapFactory.decodeStream(input)
                    dataSapi.add(ItemListSapi(id,name,price,bitmap))
                }

            }catch (e:Exception){
                Log.e("Error Http","Error $e")
            }
            return dataSapi
        }

        override fun onPostExecute(result: List<ItemListSapi>?) {
            super.onPostExecute(result)
            itemAdapter= AdapterDagingSapi(context,result!!)
            recyclerView.adapter=itemAdapter
        }
    }
}
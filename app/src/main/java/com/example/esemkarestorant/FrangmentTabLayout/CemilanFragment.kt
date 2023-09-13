package com.example.esemkarestorant.FrangmentTabLayout

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
import com.example.esemkarestorant.AdapterRecycleView.AdapterCemilanMenu
import com.example.esemkarestorant.ItemData.ItemListCemilan
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
 * Use the [CemilanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CemilanFragment : Fragment() {
    private lateinit var context: Context
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter:AdapterCemilanMenu
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_cemilan, container, false)
        context=requireContext()
        recyclerView=view.findViewById(R.id.recyleView_cemilan)
        itemAdapter=AdapterCemilanMenu(context, emptyList())
        recyclerView.adapter=itemAdapter
        getCemilan(context).execute()
        return view
    }

    inner class getCemilan(private val context: Context):AsyncTask<String,String,List<ItemListCemilan>>(){
        override fun doInBackground(vararg params: String?): List<ItemListCemilan> {
            var required=""
            var datacemilan= mutableListOf<ItemListCemilan>()
            var httpURLConnection:HttpURLConnection?=null
            var httpimage:HttpURLConnection?=null
            try {
                var url=URL("${BASEAPI.BaseApiCatrgory}Camilan")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="GET"
                httpURLConnection.setRequestProperty("Content-Type","application/json")
                httpURLConnection.setRequestProperty("accept","text/plain")

                val inputStream=httpURLConnection.inputStream
                val inputStreamReader=InputStreamReader(inputStream)
                var data =inputStream.read()
                while (data!=-1){
                    required+=data.toChar()
                    data=inputStreamReader.read()
                }
                var jsonArray=JSONArray(required)
                Log.e("ArrayListdata",jsonArray.toString())
                for (i in 0 until jsonArray.length()){
                    var jsonObject=jsonArray.getJSONObject(i)
                    var id=jsonObject.getString("id")
                    var name=jsonObject.getString("name")
                    var price=jsonObject.getInt("price").toString()
                    var urlIamge=URL(BASEAPI.BaseApi+"Api/Menu/$id/Photo")
                    httpimage=urlIamge.openConnection() as HttpURLConnection
                    httpimage.doInput=true
                    httpimage.connect()
                    var input=httpimage.inputStream
                    var bitmap=BitmapFactory.decodeStream(input)
                    datacemilan.add(ItemListCemilan(id,name,price,bitmap))
                }


            }catch (e:Exception){
                Log.e("Error Http","Error $e")
            }
            return datacemilan
        }

        override fun onPostExecute(result: List<ItemListCemilan>?) {
            super.onPostExecute(result)
            itemAdapter= AdapterCemilanMenu(context,result!!)
            recyclerView.adapter=itemAdapter
        }
    }
}
package com.example.esemkarestorant.FrangmentTabLayout

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.AdapterRecycleView.AdapterListMenu
import com.example.esemkarestorant.ItemData.ItemListTable
import com.example.esemkarestorant.R
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.databinding.FragmentAyamBinding
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
 * Use the [AyamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AyamFragment : Fragment() {
    private lateinit var binding:FragmentAyamBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemadapter:AdapterListMenu
    private lateinit var context: Context
    private lateinit var fragmentManager: FragmentManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_ayam, container, false)
        binding= FragmentAyamBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        context=requireContext()
        fragmentManager = requireActivity().supportFragmentManager
        recyclerView=view.findViewById(R.id.recyleViewAyam)
        itemadapter= AdapterListMenu(emptyList(),context, fragmentManager)
        recyclerView.adapter=itemadapter
        getDataCount(context).execute()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private inner class getDataCount( var context: Context):AsyncTask<String,String,List<ItemListTable>>(){
        override fun doInBackground(vararg params: String?): List<ItemListTable> {
            var result=""
            var id=""
            var name=""
            var price=""
            var dataItemList= mutableListOf<ItemListTable>()
            var httpURLConnection:HttpURLConnection?=null
            var htttpimage:HttpURLConnection?=null
            try {
                val url=URL("${BASEAPI.BaseApiCatrgory}Ayam")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="GET"
                httpURLConnection.setRequestProperty("Content-Type","application/json")
                httpURLConnection.setRequestProperty("accept","text/plaint")

                val inputStream=httpURLConnection.inputStream
                val inputStreamReader=InputStreamReader(inputStream)
                var data=inputStreamReader.read()

                while(data!=-1){
                    result+=data.toChar()
                    data=inputStreamReader.read()
                }

                var jsonArray=JSONArray(result)
                for (i in 0 until jsonArray.length()){
                    var jsonObject=jsonArray.getJSONObject(i)
                    id=jsonObject.getString("id")
                    name=jsonObject.getString("name")
                    price=jsonObject.getInt("price").toString()
                    val imageUrl=URL(BASEAPI.BaseApi+"Api/Menu/$id/Photo")
                    htttpimage=imageUrl.openConnection() as HttpURLConnection
                    htttpimage.doInput=true
                    htttpimage.connect()
                    val input=htttpimage.inputStream
                    val bitmap=BitmapFactory.decodeStream(input)
                    dataItemList.add(ItemListTable(id,name,price,bitmap))
                }


            }catch (e:Exception){
                Log.e("Error Http","Error $e")
            }
            return dataItemList
        }

        override fun onPostExecute(result: List<ItemListTable>?) {
            super.onPostExecute(result)
            var itemAdapterListMenu=AdapterListMenu(result!!,context, fragmentManager)
            recyclerView.adapter=itemAdapterListMenu
        }
    }
}
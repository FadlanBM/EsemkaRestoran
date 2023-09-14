package com.example.esemkarestorant.FragmentNavbar

import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esemkarestorant.AdapterRecycleView.AdapterRecyleCart
import com.example.esemkarestorant.ItemData.ItemListcart
import com.example.esemkarestorant.R
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.Util.SharePreft
import com.example.esemkarestorant.Util.SharePreft_Char
import com.example.esemkarestorant.databinding.FragmentCartBinding
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private lateinit var recyleView: RecyclerView
    private lateinit var adapterCart:AdapterRecyleCart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_cart, container, false)
        var context=requireContext()
        recyleView=view.findViewById(R.id.recyleView_cart)
        adapterCart=AdapterRecyleCart(context, emptyList(),viewLifecycleOwner)
        recyleView.adapter=adapterCart

        val button=view.findViewById<Button>(R.id.btn_cart_clear)
        button.setOnClickListener {
            SharePreft_Char(context).deleteData()
            getCount(context).execute()
        }
        getCount(context).execute()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentCartBinding.inflate(layoutInflater)
        /*var listid=SharePreft.getIntance(requireContext()).getidcart("idCarts")
        for (i in 0 until listid.size){
            Log.e("ListId", listid[i])
        }*/
        val id=SharePreft_Char(requireContext()).getData()
        val count=SharePreft_Char(requireContext()).getDataCount()
        for (data in id) {
        }


    }
    private inner class getCount(val context: Context):AsyncTask<String,String,List<ItemListcart>>(){
        override fun doInBackground(vararg params: String?): List<ItemListcart> {
            var datalist= mutableListOf<ItemListcart>()
            val id=SharePreft_Char(requireContext()).getData()
            var httpURLConnection:HttpURLConnection?=null
            var httpimage:HttpURLConnection?=null
            /*for (datacount in count){

            }*/
            for (data in id){
                var result=""
                Log.e("resul",data.toString())
                    var url=URL(BASEAPI.BaseApi+"Api/Menu/$data")
                    var urlImage=URL(BASEAPI.BaseApi+"Api/Menu/$data/Photo")
                    httpURLConnection=url.openConnection() as  HttpURLConnection
                    httpURLConnection.requestMethod="GET"
                    httpURLConnection.doInput=true
                    httpURLConnection.connect()

                    var inputStream=httpURLConnection.inputStream
                    var inputStreamReader=InputStreamReader(inputStream)
                    var data=inputStreamReader.read()
                    while (data!=-1){
                        result+=data.toChar()
                        data=inputStreamReader.read()
                    }
                    httpimage=urlImage.openConnection() as HttpURLConnection
                    httpimage.doInput=true
                    httpimage.connect()

                    val input=httpimage.inputStream
                    var bitmap=BitmapFactory.decodeStream(input)
                    var jsonObject=JSONObject(result)
                    Log.e("resul",jsonObject.toString())
                    Log.e("Bitmap",bitmap.toString())
                    Log.e("ListData",jsonObject.toString())
                    val id=jsonObject.getString("menuId")
                    var name=jsonObject.getString("name")
                    val price=jsonObject.getInt("price").toString()
                    datalist.add(ItemListcart(id,name,price,"0",bitmap))
            }
            var count=SharePreft_Char(requireContext()).getDataCount()
            count.mapIndexed { indexdata,count ->
                datalist.mapIndexed { indexdatalist, itemListcart ->
                    if (indexdata==indexdatalist){
                        itemListcart.count=count
                    }
                }
            }
            return datalist
        }

        override fun onPostExecute(result: List<ItemListcart>?) {
            super.onPostExecute(result)
            Log.e("resul",result.toString())
            var adapterCartlist= AdapterRecyleCart(context,result!!,viewLifecycleOwner)
            recyleView.adapter=adapterCartlist

        }
    }
}
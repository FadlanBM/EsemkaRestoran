package com.example.esemkarestorant

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.Util.SharePreft
import com.example.esemkarestorant.Util.SharePreft_Char
import com.example.esemkarestorant.databinding.ActivityDetailBinding
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    private var id: String?=null
    private lateinit var binding:ActivityDetailBinding
    private var startValue  =0

    private var result  = MutableLiveData<Int>()
    private val readResult:LiveData<Int> get() = result
    private var currentResult=0

    private var resultZero=MutableLiveData<Boolean>()
    private val getResultZero:LiveData<Boolean> get()=resultZero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id=intent.getStringExtra("id")
        getDetail(this,binding).execute()

        binding.countCart.text = startValue.toString()

        result.value = startValue

        readResult.observe(this) {
            binding.countCart.text = it.toString()
            resultZero.value = it == 0
            currentResult = it
        }

        getResultZero.observe(this){
            binding.btnMinCart.isEnabled = it != true
        }

        binding.btnAddCart.setOnClickListener {
            setPlus()
        }
        binding.btnMinCart.setOnClickListener {
            setMin()
        }
        binding.btnAddToCart.setOnClickListener {
            val listId = listOf(id!!)
            SharePreft.getIntance(this).saveidcart("idCarts", listId)
            val list= listOf(id)
            SharePreft_Char(this).saveData(id!!)

            val listCount= listOf(currentResult.toString())
            SharePreft.getIntance(this).savecountcart("countCart",listCount)
            SharePreft_Char(this).saveDataCount(currentResult.toString())

            val intent = Intent(this, MainMenuActivity::class.java)
            intent.putExtra("FROM_DETAIL", "FROM_DETAIL")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }

    private inner class getDetail(val context:Context,private val binding: ActivityDetailBinding):AsyncTask<String,String,String>(){

        override fun doInBackground(vararg params: String?): String {
            var result=""
            var httpURLConnection:HttpURLConnection?=null
            var htttpImage:HttpURLConnection?=null
            try {
                var url=URL(BASEAPI.BaseApi+"Api/Menu/$id")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="GET"
                httpURLConnection.setRequestProperty("Content-Type","application/josn")
                httpURLConnection.setRequestProperty("accept","text/plain")

                val inputStream=httpURLConnection.inputStream
                var inputStreamReader=InputStreamReader(inputStream)
                var data=inputStreamReader.read()
                while (data!=-1){
                    result+=data.toChar()
                    data=inputStreamReader.read()
                }

                var urlImage=URL(BASEAPI.BaseApi+"Api/Menu/$id/Photo")
                htttpImage=urlImage.openConnection() as HttpURLConnection
                htttpImage.doInput=true
                htttpImage.connect()
                var input=htttpImage.inputStream
                var bitmap=BitmapFactory.decodeStream(input)
                binding.imImageDetail.setImageBitmap(bitmap)
            }catch (e:Exception){
                Log.e("Error Http","Error $e")
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var jsonObject=JSONObject(result)
            binding.lbJudulProduct.text=jsonObject.getString("name")
            binding.lbPriceProduct.text=jsonObject.getInt("price").toString()
            binding.lbDescProduct.text=jsonObject.getString("description")
        }

    }
        fun setPlus(){
            result.value=(result.value)?.plus(1)
            Log.e("RESULT_VALUE", result.value.toString())
        }
        fun setMin(){
            result.value=(result.value)?.minus(1)
        }
}
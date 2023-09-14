package com.example.esemkarestorant

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.esemkarestorant.Util.BASEAPI
import com.example.esemkarestorant.Util.SharePreft
import com.example.esemkarestorant.databinding.ActivityLoginAdminBinding
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class Login_Admin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            var status=true
            if (binding.lbInputEmail.length() == 0) {
                status=false
                binding.lbInputEmail.setError("Form email belum di isi")
            }
            if (binding.lbInputPasssword.length() == 0) {
                status=false
                binding.lbInputPasssword.setError("Form Password belum di isi")
            }
            if (!status){
                return@setOnClickListener
            }
            getToken(this).execute()
        }
    }

    private inner class getToken(val context: Context):AsyncTask<String,String,String>(){
        override fun doInBackground(vararg p0: String?): String {
            var result=""
            var jsonObject=JSONObject()
            jsonObject.put("email","iclarricoates3@clickbank.net")
            jsonObject.put("password","fTa9aI71rEm")
            var jsonObjectString=jsonObject.toString()
            var httpURLConnection:HttpURLConnection?=null
            try {
                var url=URL(BASEAPI.BaseApi+"Api/Auth")
                httpURLConnection=url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod="POST"
                httpURLConnection.setRequestProperty("Content-Type","application/json")
                httpURLConnection.connect()

                var output=httpURLConnection.outputStream
                var outputWriter=OutputStreamWriter(output)
                outputWriter.write(jsonObjectString)
                outputWriter.flush()

                var input=httpURLConnection.inputStream
                var inputStremReader=InputStreamReader(input)
                var data=inputStremReader.read()

                while (data!=-1){
                    result+=data.toChar()
                    data=inputStremReader.read()
                }
                if (httpURLConnection.responseCode==HttpURLConnection.HTTP_BAD_REQUEST){
                    Handler(context.mainLooper).post(Runnable {
                        Toast.makeText(context,"Password/Username yang anda masukkan salah",Toast.LENGTH_SHORT).show()
                    })
                }

            }catch (e:Exception){
                Log.e("Error Http","Error Http $e")
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result==""){
                return
            }
            var jsonObject=JSONObject(result)
            var token= jsonObject.getString("token")
            SharePreft.getIntance(context).setToken(token)
            startActivity(Intent(context,MainListTableAdminActivity::class.java))
        }
    }
}
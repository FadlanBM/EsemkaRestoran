package com.example.esemkarestorant.Util

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class SharePreft constructor(context: Context) {
    companion object{
        private const val preftNama="MyApp"
        private const val preftToken="token"
        private const val idListTable="asdsa"
        private const val idCarts ="idCarts0"

        @Volatile
        private var intance:SharePreft?=null

        fun getIntance(context: Context):SharePreft=
            intance?: synchronized(this){
                intance?: SharePreft(context.applicationContext).also { intance=it }
            }
    }
    private val sharePreft:SharedPreferences=
        context.getSharedPreferences(preftNama,Context.MODE_PRIVATE)

    fun setToken(token:String){
        sharePreft.edit().putString(preftToken,token).apply()
    }
    fun getToken():String?{
        return sharePreft.getString(preftToken,null)
    }
}
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
    fun setidListTable(idlist:String){
        sharePreft.edit().putString(idListTable,idlist).apply()
    }

    fun getToken():String?{
        return sharePreft.getString(preftToken,null)
    }
    fun setidlist(idlist:String){
        sharePreft.edit().putString(idListTable,idlist).apply()
    }

    fun getidlist():String?{
        return sharePreft.getString(idCarts,null)
    }

    fun getIdList():String?{
        return sharePreft.getString(idCarts,null)
    }

    fun saveidcart(key: String, list: List<String>) {
        val joinedList = list.joinToString(separator = "|") // Use any separator you prefer
        sharePreft.edit().putString(key, joinedList).apply()
    }

    fun getidcart(key: String): List<String> {
        val joinedList = sharePreft.getString(key, null)
        return joinedList?.split("|") ?: emptyList() // Use the same separator you used when saving
    }
    fun savecountcart(key: String, list: List<String>) {
        val joinedList = list.joinToString(separator = "|") // Use any separator you prefer
        sharePreft.edit().putString(key, joinedList).apply()
    }

    fun getcountcart(key: String): List<String> {
        val joinedList = sharePreft.getString(key, null)
        return joinedList?.split("|") ?: emptyList() // Use the same separator you used when saving
    }


}
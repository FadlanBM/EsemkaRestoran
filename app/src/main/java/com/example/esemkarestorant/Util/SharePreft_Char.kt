package com.example.esemkarestorant.Util

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class SharePreft_Char(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    private val editor2: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveData(data: String) {
        val jsonArray = JSONArray()
        val existingData = sharedPreferences.getString("my_data_key", null)

        if (existingData != null) {
            val existingArray = JSONArray(existingData)
            for (i in 0 until existingArray.length()) {
                jsonArray.put(existingArray.getString(i))
            }
        }

        jsonArray.put(data)

        editor.putString("my_data_key", jsonArray.toString())
        editor.apply()
    }

    fun getData(): List<String> {
        val existingData = sharedPreferences.getString("my_data_key", null)
        val dataList = mutableListOf<String>()

        if (existingData != null) {
            val jsonArray = JSONArray(existingData)
            for (i in 0 until jsonArray.length()) {
                val data = jsonArray.getString(i)
                dataList.add(data)
            }
        }

        return dataList
    }
    fun saveDataCount(data: String) {
        val jsonArray = JSONArray()
        val existingData = sharedPreferences.getString("my_count_key", null)

        if (existingData != null) {
            val existingArray = JSONArray(existingData)
            for (i in 0 until existingArray.length()) {
                jsonArray.put(existingArray.getString(i))
            }
        }

        jsonArray.put(data)

        editor2.putString("my_count_key", jsonArray.toString())
        editor2.apply()
    }
    fun updateDataCount( potition:Int,newData: String) {
        val existingData = sharedPreferences.getString("my_count_key", null)

        if (existingData != null) {
            val jsonArray = JSONArray(existingData)

            // Cari indeks elemen yang ingin Anda update
            for (i in 0 until jsonArray.length()) {
                if (i == potition) {
                    // Update data di indeks tersebut dengan newData
                    jsonArray.put(i, newData)
                    break // Keluar dari loop setelah data diupdate
                }
            }

            // Simpan JSON Array yang telah diubah kembali ke Shared Preferences
            editor2.putString("my_count_key", jsonArray.toString())
            editor2.apply()
        }
    }
    fun deleteDataCount( potition:Int) {
        val existingDataCount = sharedPreferences.getString("my_count_key", null)
        val existingData = sharedPreferences.getString("my_data_key", null)

        if (existingData != null) {
            val jsonArray = JSONArray(existingData)
            val jsonArrayCount = JSONArray(existingDataCount)

            // Cari indeks elemen yang ingin Anda update
            for (i in 0 until jsonArray.length()) {
                if (i == potition) {
                    // Update data di indeks tersebut dengan newData
                    jsonArray.remove(i)
                    jsonArrayCount.remove(i)
                    break // Keluar dari loop setelah data diupdate
                }
            }

            // Simpan JSON Array yang telah diubah kembali ke Shared Preferences
            editor2.putString("my_count_key", jsonArrayCount.toString())
            editor2.apply()
            editor.putString("my_data_key", jsonArray.toString())
            editor.apply()
        }
    }

    fun getDataCount(): List<String> {
        val existingData = sharedPreferences.getString("my_count_key", null)
        val dataList = mutableListOf<String>()

        if (existingData != null) {
            val jsonArray = JSONArray(existingData)
            for (i in 0 until jsonArray.length()) {
                val data = jsonArray.getString(i)
                dataList.add(data)
            }
        }

        return dataList
    }

    fun deleteData(){
        editor.clear()
        editor.apply()
    }

}
package com.example.esemkarestorant.FragmentNavbar

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.esemkarestorant.ItemData.ItemListcart
import com.example.esemkarestorant.R
import com.example.esemkarestorant.Util.SharePreft

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listid=SharePreft.getIntance(requireContext()).getidcart("idCarts")
        for (i in 0 until listid.size){
            Log.e("ListId", listid[i])
        }
    }
    private inner class getCount(val context: Context):AsyncTask<String,String,List<ItemListcart>>(){
        override fun doInBackground(vararg params: String?): List<ItemListcart> {
            var dataItemListCart = mutableListOf<ItemListcart>()
            var listid=SharePreft.getIntance(context).getidcart("idCarts")
            for (i in 0 until listid.size){
                Log.e("ListId", listid[i])
            }
            return dataItemListCart
        }

        override fun onPostExecute(result: List<ItemListcart>?) {
            super.onPostExecute(result)
        }
    }
}
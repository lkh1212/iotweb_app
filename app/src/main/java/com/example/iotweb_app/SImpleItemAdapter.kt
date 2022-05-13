package com.example.iotweb_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_item.view.*

class SImpleItemAdapter(var context: Context, var itemLayout:Int, var datalist:ArrayList<SimpleItem>, var imagelist:ArrayList<Int>)
    :RecyclerView.Adapter<SImpleItemAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textview = itemView.itemview_info
        val imageView = itemView.imageview1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout,null)
        val myViewHolder = MyViewHolder(itemView)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var myTextView = holder.textview
        var myImageVIew = holder.imageView
        myTextView.text = datalist.get(position).title
        myImageVIew.setImageResource(imagelist[position])
        Log.d("test", "onBindViewHolder")
        myTextView.setOnClickListener {
            when (position) {
                0 -> {
                    val intent = Intent(context, RoomLightActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                1 -> {
                    val intent = Intent(context, CctvActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                2 -> {
                    val intent = Intent(context, CurtainActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                3 -> {
                    val intent = Intent(context, AlarmActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                4 -> {
                    val intent = Intent(context, PetActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                5 -> {
                    val intent = Intent(context, TempActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
            }


        }
        myImageVIew.setOnClickListener {
            when (position) {
                0 -> {
                    val intent = Intent(context, RoomLightActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                1 -> {
                    val intent = Intent(context, CctvActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                2 -> {
                    val intent = Intent(context, CurtainActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                3 -> {
                    val intent = Intent(context, AlarmActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                4 -> {
                    val intent = Intent(context, PetActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                5 -> {
                    val intent = Intent(context, TempActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
            }
        }
    }



    override fun getItemCount(): Int {
        return datalist.size
    }


}
package com.example.ca2

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MailAdapter(var mCtx: Context, var resources:Int, var items2:List<Mail>)
    : ArrayAdapter<Mail>(mCtx,resources,items2) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val title: TextView = view.findViewById(R.id.title)
        val message: TextView = view.findViewById(R.id.message)

        val mItem: Mail = items2[position]

        title.text = mItem.title
        message.text = mItem.message


        return view

    }
}

package com.example.ca2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MailAdapter(var mCtx: Context, var resources:Int, var items2:List<Mail>)
    : ArrayAdapter<Mail>(mCtx,resources,items2) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val title: TextView = view.findViewById(R.id.title)
        val message: TextView = view.findViewById(R.id.message)
        val card: androidx.cardview.widget.CardView = view.findViewById(R.id.card)

        val mItem: Mail = items2[position]

        title.text = mItem.title
        message.text = mItem.message

        card.setOnClickListener {
            val intent = Intent(mCtx, MailItem::class.java)
            intent.putExtra("from", mItem.from)
            intent.putExtra("to", mItem.to)
            intent.putExtra("cc", mItem.cc)
            intent.putExtra("bcc", mItem.bcc)
            intent.putExtra("title", mItem.title)
            intent.putExtra("message", mItem.message)
            mCtx.startActivity(intent)
        }
        return view
    }
}

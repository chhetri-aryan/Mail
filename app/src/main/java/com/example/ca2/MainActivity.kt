package com.example.ca2

import SQLDBHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, MailActivity::class.java)
            startActivity(intent)
        }

        val listview = findViewById<ListView>(R.id.listview)
        val db = SQLDBHelper(this)
        val mails = db.getMails()
        val list = ArrayList<Mail>()
        for (i in mails) {
            list.add(i)
        }

        listview.adapter = MailAdapter(this, R.layout.mails, list)

        Log.d("mails", "onCreate: $mails")

        if (list.isEmpty()) {

            val noMailsTextView = TextView(this)
            noMailsTextView.text = "No mails Sent"
            noMailsTextView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            noMailsTextView.setPadding(400)
            noMailsTextView.textSize = 24F

            val parentLayout = findViewById<ViewGroup>(R.id.main)
            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            noMailsTextView.layoutParams = layoutParams

            parentLayout.addView(noMailsTextView)
            listview.emptyView = noMailsTextView
        }

    }
}

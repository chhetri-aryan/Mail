package com.example.ca2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MailItem : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mail_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sender = findViewById<TextView>(R.id.sender)
        val receiver = findViewById<TextView>(R.id.receiver)
        val bcc = findViewById<TextView>(R.id.bcc)
        val cc = findViewById<TextView>(R.id.cc)
        val title = findViewById<TextView>(R.id.title)
        val message = findViewById<TextView>(R.id.message)

        sender.text = intent.getStringExtra("from")
        receiver.text = intent.getStringExtra("to")
        bcc.text = intent.getStringExtra("bcc")
        cc.text = intent.getStringExtra("cc")
        title.text = intent.getStringExtra("title")
        message.text = intent.getStringExtra("message")

        val back = findViewById<Button>(R.id.back1)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

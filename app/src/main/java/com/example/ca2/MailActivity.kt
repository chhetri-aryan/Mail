package com.example.ca2
import SQLDBHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // shared pref for storing sender email
        val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val sender = sharedPref.getString("sender", "")
        val from = findViewById<EditText>(R.id.from)
        from.setText(sender)

        val back = findViewById<Button>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val send = findViewById<Button>(R.id.send)
        send.setOnClickListener {
            val from = findViewById<EditText>(R.id.from).text.toString()
            val to = findViewById<EditText>(R.id.to).text.toString()
            val cc = findViewById<EditText>(R.id.cc).text.toString()
            val bcc = findViewById<EditText>(R.id.bcc).text.toString()
            val title = findViewById<EditText>(R.id.title).text.toString()
            val message = findViewById<EditText>(R.id.message).text.toString()
            val db = SQLDBHelper(this)

            if (from.isEmpty() || to.isEmpty() || title.isEmpty() || message.isEmpty()) {
                makeToast(this, "Please make sure all the necessary details are filled")
            }

            else if (!validateSender(from)) {
                makeToast(this, "Please enter a valid Sender address")
            }

            else if (!validate(to)) {
                makeToast(this, "Please enter a valid Receiver address")
            }

            else if (cc.isNotEmpty() && !validate(bcc)) {
                makeToast(this, "Please enter a valid BCC address")
            }
            else if (cc.isNotEmpty() && !validate(cc)) {
                makeToast(this, "Please enter a valid CC address")
            }

            else {
                db.addMail(Mail(from, to, cc, bcc, title, message))
                sharedPref.edit().putString("sender", from).apply()
                Toast.makeText(this, "Mail Successfully sent to $to", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}

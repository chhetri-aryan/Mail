package com.example.ca2

import android.content.Context
import android.widget.Toast


fun validateSender(email: String) : Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}

fun validate(email: String) : Boolean {
    //email validation

    val listEmail = email.trim().split( " ")
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    for (i in listEmail){
        if (!i.matches(emailPattern.toRegex())){
            return false
        }
    }
    return true
}

fun makeToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

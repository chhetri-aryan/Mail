package com.example.ca2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "MAIL.DB"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "MAIL"
        const val COLUMN_ID = "ID"
        const val COLUMN_FROM = "FROM"
        const val COLUMN_TO = "TO"
        const val COLUMN_CC = "CC"
        const val COLUMN_BCC = "BCC"
        const val COLUMN_TITLE = "TITLE"
        const val COLUMN_MESSAGE = "MESSAGE"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            "$COLUMN_FROM" TEXT,
            "$COLUMN_TO" TEXT,
            "$COLUMN_CC" TEXT,
            "$COLUMN_BCC" TEXT,
            "$COLUMN_TITLE" TEXT,
            "$COLUMN_MESSAGE" TEXT
        )
    """.trimIndent()
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addMail(mail: Mail) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("CC", mail.cc)
            put("\"TO\"", mail.to)
            put("BCC", mail.bcc)
            put("\"FROM\"", mail.from)
            put("TITLE", mail.title)
            put("MESSAGE", mail.message)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getMails() : List<Mail> {
        val result = mutableListOf<Mail>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
              result.add(Mail(cursor.getString(cursor.getColumnIndex(COLUMN_FROM)),
                      cursor.getString(cursor.getColumnIndex(COLUMN_TO)),
                      cursor.getString(cursor.getColumnIndex(COLUMN_CC)),
                      cursor.getString(cursor.getColumnIndex(COLUMN_BCC)),
                      cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                      cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE))))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return result
    }

}
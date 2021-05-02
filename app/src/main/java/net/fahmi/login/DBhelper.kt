package net.fahmi.login

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context) : SQLiteOpenHelper(context,db_name,null,2) {

    val db = writableDatabase

    companion object{
        public const val  db_name = "login"
        public const val  tb_name = "user"

        public const val row_id ="_id"
        public const val row_user ="username"
        public const val row_pass ="pass"

    }
    override fun onCreate(d: SQLiteDatabase) {
        val sql : String? = "create table "+ tb_name+" ("+ row_id+" integer primary key autoincrement, " +
                row_user+" varchar, "+ row_pass+" varchar)"
        d.execSQL(sql)
    }

    override fun onUpgrade(d: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        d.execSQL("delete table if exists "+ db_name)
    }
    public fun insertData(values: ContentValues?){
        db.insert(tb_name,null,values)
    }
    public fun checkLogin( user: String?, pass: String?) : Boolean{
        val columns  = arrayOf(row_id)
        val db = readableDatabase
        val selection :String?= row_user+" =? and "+ row_pass+" =?"
        val selectionArgs = arrayOf(user,pass)
        val cur : Cursor = db.query(tb_name,columns,selection,selectionArgs,null,null,null)
        val count :Int? =cur.count
        if (count!! > 0) {
            return true
        }else {
            return false
        }


    }
}
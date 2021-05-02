package net.fahmi.login

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val user = findViewById<EditText>(R.id.reg_user)
        val pass = findViewById<EditText>(R.id.reg_pass)
        val pass2 = findViewById<EditText>(R.id.reg_pass2)
        val register = findViewById<Button>(R.id.register)
        val login = findViewById<TextView>(R.id.RLogin)
        val db = DBhelper(this)

        login.setText(fromHtml("Back to </font><font color='#FF3700B3'>login</font>"))
        login.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        register.setOnClickListener{
            val username:String? = user.text.toString().trim()
            val password : String? = pass.text.toString().trim()
            val password2 : String? = pass2.text.toString().trim()
            val cv : ContentValues?= ContentValues()
            if (username.equals("")|| password.equals("")||password2.equals("")){
                Toast.makeText(this,"Empty not permitted", Toast.LENGTH_SHORT).show()
            }else if(!password.equals(password2)){
                Toast.makeText(this,"Password not match", Toast.LENGTH_SHORT).show()
            }else{
                cv?.put(DBhelper.row_user,username)
                cv?.put(DBhelper.row_pass,password)
                db.insertData(cv)
                Toast.makeText(this,"Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    fun fromHtml(html:String) : Spanned {
        val result : Spanned
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        }else{
            result = Html.fromHtml(html)
        }
        return result
    }
}
package net.fahmi.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = findViewById<EditText>(R.id.login_user)
        val pass = findViewById<EditText>(R.id.login_pass)
        val login = findViewById<Button>(R.id.login)
        val register = findViewById<TextView>(R.id.Lregister)
        val db = DBhelper(this)
        register.setText(fromHtml("Create</font><font color='#FF3700B3'> account</font>"))
        register.setOnClickListener{
                    startActivity(Intent(this,Register::class.java))
        }
        login.setOnClickListener{
            val username:String? = user.text.toString()
            val password : String? = pass.text.toString()
            val result : Boolean = db.checkLogin(username,password)
            if (username.equals("")|| password.equals("")){
                Toast.makeText(this,"Empty not permitted",Toast.LENGTH_SHORT).show()
            }else if(result == true){
                Toast.makeText(this,"Login Succesful",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
            }else{
                Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
     fun fromHtml(html:String) :Spanned{
        val result :Spanned
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, FROM_HTML_MODE_LEGACY)
        }else{
            result = Html.fromHtml(html)
        }
        return result
    }


}
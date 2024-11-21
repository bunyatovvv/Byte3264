package com.behmennnn.byte3264

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.behmennnn.byte3264.databinding.ActivityLoginFormBinding

class Login : AppCompatActivity() {
    private lateinit var bind : ActivityLoginFormBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)
        var dbhelp=Database(applicationContext)
        var db=dbhelp.readableDatabase
        bind.btnlogin.setOnClickListener {
            var username=bind.logtxt.text.toString();
            var password=bind.ed3.text.toString()
            val query="SELECT * FROM user WHERE username='"+username+"' AND pswd='"+password+"'"
            val rs=db.rawQuery(query,null)
            if(rs.moveToFirst()){
                val name=rs.getString(rs.getColumnIndex("name"))
                rs.close()
                startActivity(Intent(this,Welcome::class.java).putExtra("name",name))
            }
            else{
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Mesaj")
                ad.setMessage("Login sehvdir")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }
        bind.regisLink.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
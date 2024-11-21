package com.behmennnn.byte3264

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.behmennnn.byte3264.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var dbhelp=Database(applicationContext)
        var db=dbhelp.writableDatabase
        binding.btnrgs.setOnClickListener {
            var username=binding.ed2.text.toString()
            var password=binding.ed3.text.toString()
            if(username.isNotEmpty() && password.isNotEmpty()) {
                var data = ContentValues()
                data.put("username", binding.ed2.text.toString())
                data.put("pswd", binding.ed3.text.toString())
                var rs:Long = db.insert("user", null, data)
                if(!rs.equals(-1)) {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Mesaj")
                    ad.setMessage("Qeydiyyat ugurludur!")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }else{
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Xana bosdur")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }
            }else{
                Toast.makeText(this,"Butun xanalar dolmalidir", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginLink.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}
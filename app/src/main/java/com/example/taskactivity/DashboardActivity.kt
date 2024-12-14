package com.example.taskactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    private lateinit var fullname:TextView
    private lateinit var email:TextView
    private lateinit var gender:TextView
    private lateinit var country:TextView
    private lateinit var city:TextView
    private lateinit var back:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        fullname=findViewById(R.id.fullNameOutput)
        email=findViewById(R.id.emailOutput)
        gender=findViewById(R.id.genderOutput)
        country=findViewById(R.id.countryOutput)
        city=findViewById(R.id.cityOutput)
        back=findViewById(R.id.backButtonDashboard)


        fullname.text= intent.getStringExtra("name")
        email.text= intent.getStringExtra("email")
        gender.text= intent.getStringExtra("gender")
        country.text= intent.getStringExtra("country")
        city.text= intent.getStringExtra("city")

        back.setOnClickListener {
            intent= Intent(this@DashboardActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
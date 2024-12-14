package com.example.taskactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var male: RadioButton
    private lateinit var female: RadioButton
    private lateinit var countryDrop: Spinner
    private lateinit var cityAuto: AutoCompleteTextView
    private lateinit var agreement: CheckBox
    private lateinit var submit: Button
    private lateinit var gender: String
    private lateinit var city: Array<String>
    private lateinit var selectedCountry: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        nameInput = findViewById(R.id.nameInput)
        submit = findViewById(R.id.submitButton)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        agreement = findViewById(R.id.termsBox)
        male = findViewById(R.id.MaleOption)
        female = findViewById(R.id.femaleOption)
        countryDrop = findViewById(R.id.countrySpinner)
        cityAuto = findViewById(R.id.cityAutoText)

        // Set up gender radio buttons
        male.setOnClickListener {
            var gender = "Male"
        }
        female.setOnClickListener {
            var gender = "Female"
        }

        // Set up country spinner
        val countries = arrayOf("Nepal", "America", "India", "Japan")
        val spinAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_dropdown_item,
            countries
        )
        countryDrop.onItemSelectedListener = this
        countryDrop.adapter = spinAdapter

        // Set up submit button click listener
        submit.setOnClickListener {
            val name = nameInput.text.toString()
            val password = passwordInput.text.toString()
            val email = emailInput.text.toString()
            val city = cityAuto.text.toString()
            if (agreement.isChecked) {
                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("city", city)
                intent.putExtra("gender", gender)
                intent.putExtra("country",selectedCountry)
                startActivity(intent)

            }
        }

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            selectedCountry = parent.getItemAtPosition(position).toString()
            updateCityAutoComplete(selectedCountry)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Handle no item selected case
    }

    private fun updateCityAutoComplete(country: String) {
        city = when (country) {
            "Nepal" -> arrayOf("Kathmandu", "Lalitpur", "Butwal", "Biratnagar", "Janakpur")
            "America" -> arrayOf("Washington DC", "Texas", "LA", "San Francisco")
            "India" -> arrayOf("Delhi", "Mumbai", "Bangalore", "Rajasthan")
            "Japan" -> arrayOf("Tokyo", "Kyoto", "Hokkaido", "Nagasaki")
            else -> arrayOf()
        }

        val cityAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_dropdown_item_1line,
            city
        )
        cityAuto.setAdapter(cityAdapter)
        cityAuto.threshold = 1
    }
}

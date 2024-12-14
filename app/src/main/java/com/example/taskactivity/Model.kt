package com.example.taskactivity

import java.io.Serializable

class Model : Serializable {
    var fullName: String? = null
    var email: String? = null
    var password: String? = null
    var gender: String? = null
    var country: String? = null
    var city: String? = null // No need for the model() function here
}
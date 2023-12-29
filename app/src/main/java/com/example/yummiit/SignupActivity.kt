package com.example.yummiit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.yummiit.api.UserApi
import kotlinx.coroutines.*
import java.net.HttpURLConnection

class SignupActivity : AppCompatActivity() {

    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtConfirmPassword: EditText

    private lateinit var btnRegister: Button
    private lateinit var btnLogin: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        txtUsername = findViewById(R.id.username)
        txtPassword = findViewById(R.id.password)
        txtEmail = findViewById(R.id.email)
        txtConfirmPassword = findViewById(R.id.confirmPassword)

        btnRegister = findViewById(R.id.registerButton)
        btnRegister.setOnClickListener { registerClicked() }

        btnLogin = findViewById(R.id.loginNowButton)
        btnLogin.setOnClickListener { loginClicked() }
    }

    private fun registerClicked() {
        val username = txtUsername.text.toString()
        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()
        val confirmPassword = txtConfirmPassword.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_LONG).show()
            return
        } else if (password != confirmPassword) {
            Toast.makeText(this, "password don't match", Toast.LENGTH_LONG).show()
            return
        }

        val user = User(username = username, email = email, password = password, confirmPassword = confirmPassword)
        CoroutineScope(Dispatchers.IO).launch {

            val response = UserApi.register(user)
            if (response.statusCode != HttpURLConnection.HTTP_OK) {
                withContext(Dispatchers.Main) {
                    val error = response.getError()
                    Toast.makeText(this@SignupActivity, error.message, Toast.LENGTH_LONG).show()
                }

                return@launch
            }

            withContext(Dispatchers.Main) {
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun loginClicked() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
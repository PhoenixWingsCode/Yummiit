package com.example.yummiit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.yummiit.api.UserApi
import com.example.yummiit.api.UserApi.Companion.asUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection

class LoginActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText

    private lateinit var btnLogin: AppCompatButton
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtEmail = findViewById(R.id.email)
        txtPassword = findViewById(R.id.password)

        btnLogin = findViewById(R.id.loginButton)
        btnLogin.setOnClickListener { loginClicked() }

        btnRegister = findViewById(R.id.registerNowButton)
        btnRegister.setOnClickListener { registerClicked() }
    }

    private fun registerClicked() {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    private fun loginClicked() {
        try {
            val user = User(
                email = txtEmail.text.toString(),
                password = txtPassword.text.toString()
            )

            CoroutineScope(Dispatchers.IO).launch {

                val response = UserApi.login(user)
                if (response.statusCode != HttpURLConnection.HTTP_OK) {
                    val error = response.getError()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            error.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    return@launch
                }

                withContext(Dispatchers.Main) {
                    val loginUser = response.asUser()

                    val prefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
                    val editor = prefs.edit()

                    editor.putInt("user_id", loginUser.id)
                    editor.apply()

                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)

                    finish()
                }
            }

        } catch (ex: Exception) {
            Log.i("exception", ex.toString())
        }
    }
}
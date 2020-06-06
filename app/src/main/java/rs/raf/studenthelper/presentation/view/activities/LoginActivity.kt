package rs.raf.studenthelper.presentation.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.studenthelper.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    companion object {
        const val USER_NAME = "userNameKey"
        const val PIN_NUMBER = "pinNumberKey"
        const val HARDCODED_PIN = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        btnLogin.setOnClickListener {
            var errorMessage: String = ""
            if (usernameEt.text.isEmpty()) {
                errorMessage = "Insert username"
            } else if (pinEt.text.length < 4) {
                errorMessage = "PIN must be 4 digits"
            } else {
                val enteredPin = pinEt.text.toString()
                if (enteredPin == HARDCODED_PIN) {
                    val username = usernameEt.text.toString()

                    val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(USER_NAME, username)
                    editor.putString(PIN_NUMBER, enteredPin)
                    editor.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    errorMessage = "Wrong PIN"
                }
            }

            if (errorMessage.isNotEmpty())
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}

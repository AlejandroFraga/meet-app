package com.example.alejandro.myapplication.activities.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.InitActivity
import com.example.alejandro.myapplication.R
import com.google.firebase.auth.FirebaseAuth

//Actividade de inicio de sesión

class LoginActivity : BaseActivity(), View.OnClickListener {

    private var mAuth: FirebaseAuth? = null

    private var loginEmailField: EditText? = null
    private var loginPasswordField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Views
        loginEmailField = findViewById(R.id.login_email_field)
        loginPasswordField = findViewById(R.id.login_password_field)

        // Buttons
        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()
    }

    //En caso de ter unha sesión xa iniciada, imos de volta á páxina Inir
    override fun onStart() {
        super.onStart()
        if (mAuth?.currentUser != null) {
            goToInitActivity()
        }
    }

    //Función para facer un intento de inicio de sesión a traves de correo e contrasinal
    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        goToInitActivity()
                    } else {
                        Toast.makeText(this@LoginActivity, getString(R.string.auth_failed),
                                Toast.LENGTH_SHORT).show()
                    }
                    hideProgressDialog()
                }
    }

    //Función para comprobar que o correo e o contrasinal estan cubertos
    private fun validateForm(): Boolean {
        var valid = true

        val email = loginEmailField!!.text.toString()
        if (TextUtils.isEmpty(email)) {
            loginEmailField?.error = getString(R.string.required)
            valid = false
        } else {
            loginEmailField?.error = null
        }

        val password = loginPasswordField?.text.toString()
        if (TextUtils.isEmpty(password)) {
            loginPasswordField?.error = getString(R.string.required)
            valid = false
        } else {
            loginPasswordField?.error = null
        }

        return valid
    }

    //Función do boton de inicio de sesion
    override fun onClick(v: View) {
        signIn(loginEmailField?.text.toString(), loginPasswordField?.text.toString())
    }

    //Función para redirixir á páxina de cargado de información
    private fun goToInitActivity() {
        val intent = Intent(this, InitActivity::class.java)
        startActivity(intent)
    }
}
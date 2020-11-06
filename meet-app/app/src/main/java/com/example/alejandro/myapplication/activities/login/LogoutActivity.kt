package com.example.alejandro.myapplication.activities.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.alejandro.myapplication.BaseActivity
import com.example.alejandro.myapplication.MainActivity
import com.example.alejandro.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import io.realm.Realm

//Actividade de pechado de sesión

class LogoutActivity : BaseActivity(), View.OnClickListener {

    private val realm = Realm.getDefaultInstance()
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val signOutButton = findViewById<Button>(R.id.sign_out_button)
        signOutButton?.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()
    }

    //En caso de non ter unha sesión xa iniciada, imos de volta á páxina principal
    override fun onStart() {
        super.onStart()
        if (mAuth?.currentUser == null) {
            goToMainActivity()
        }
    }

    //Función do botón de pechado de sesión, que lanzara un dialoginterface para que fagamos
    //unha confirmación da accion que acabamos de realizar
    override fun onClick(v: View) {
        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    singOut()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    dialog.dismiss()
                }
            }
        }
        AlertDialog.Builder(this).setMessage(getString(R.string.logout_warning_text))
                .setPositiveButton(getString(R.string.accept), dialogClickListener)
                .setNegativeButton(getString(R.string.decline), dialogClickListener).show()
    }

    //Funciçón de pechado de sesión
    private fun singOut() {
        showProgressDialog()
        mAuth?.signOut()
        cleanDB()
        goToMainActivity()
        hideProgressDialog()
    }

    //Funciçón de limpado da BBDD
    private fun cleanDB() {
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

    //Funciçón de redireccionado á páxina principal
    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
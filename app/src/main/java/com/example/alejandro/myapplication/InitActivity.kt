package com.example.alejandro.myapplication

import android.content.Intent
import android.os.Bundle
import com.example.alejandro.myapplication.facades.ControllerFacade
import com.example.alejandro.myapplication.utils.getUserUID

//Actividade Init, a cal iniciará o proceso de cargado da información do paciente se este está logueado

class InitActivity : BaseActivity() {

    val controllerFacade: ControllerFacade = ControllerFacade(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        iniciar()
    }

    //Función para non poder voltar á actividade anterior
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }

    private fun iniciar() {
        if (getUserUID() != null) {
            controllerFacade.init()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

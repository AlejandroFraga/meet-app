package com.example.alejandro.myapplication

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.example.alejandro.myapplication.controllers.database.RealmMigrations
import io.realm.Realm
import io.realm.RealmConfiguration

//Actividade creada para o iniciado unha vez por execución da aplicación do Realm

class RealmInitActivity : BaseActivity() {

    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm_init)

        iniciar()
    }

    //Función para non poder voltar á actividade anterior
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }

    private fun iniciar() {
        //Código para a eliminación completa do Realm Default
//        Realm.deleteRealm(Realm.getDefaultConfiguration())

        //Función de iniciado do Realm
        Realm.init(this)

        //Código para a migración de BBDD
//        val configuration = RealmConfiguration.Builder().name("sample.realm").schemaVersion(2).migration(RealmMigrations()).build()
//        Realm.setDefaultConfiguration(configuration)

        //Recuperado da BBDD
        realm = Realm.getDefaultInstance()

        //Función para o limpado da BBDD, xa que actualmente aínda non estará implementada a combinacion local e remota inicial
        cleanDB()

        iniciarInitActivity()
    }

    //Función para o limpado da BBDD
    private fun cleanDB() {
        realm?.beginTransaction()
        realm?.deleteAll()
        realm?.commitTransaction()
    }

    private fun iniciarInitActivity() {
        val intent = Intent(this, InitActivity::class.java)
        startActivity(intent)
    }

}
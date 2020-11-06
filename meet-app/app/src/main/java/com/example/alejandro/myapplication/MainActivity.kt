package com.example.alejandro.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.alejandro.myapplication.activities.calendar.CalendarActivity
import com.example.alejandro.myapplication.activities.chat.ChatActivity
import com.example.alejandro.myapplication.activities.feedback.ListaFeedbacksActivity
import com.example.alejandro.myapplication.activities.login.LoginActivity
import com.example.alejandro.myapplication.activities.login.LogoutActivity
import com.example.alejandro.myapplication.activities.notifications.NotificationsActivity
import com.example.alejandro.myapplication.activities.patientdata.PatientDataActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

//Actividade principal

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        checkUser()
    }

    override fun onRestart() {
        super.onRestart()
        checkUser()
    }

    override fun onResume() {
        super.onResume()
        checkUser()
    }

    private fun checkUser(){
        currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    //Función para controlar que opcións do menú están habilitadas e cales non
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            nav_view.menu.findItem(R.id.nav_log_in).isVisible = false
            nav_view.menu.findItem(R.id.nav_patient_data).isVisible = true
            nav_view.menu.findItem(R.id.nav_feedback).isVisible = true
            nav_view.menu.findItem(R.id.nav_calendar).isVisible = true
            nav_view.menu.findItem(R.id.nav_chat).isVisible = true
            nav_view.menu.findItem(R.id.nav_notifications).isVisible = true

            //A opción de configuración estará desactivada por agora ata a súa implementación
            nav_view.menu.findItem(R.id.nav_manage).isVisible = false
            nav_view.menu.findItem(R.id.nav_log_out).isVisible = true
        } else {
            nav_view.menu.findItem(R.id.nav_log_in).isVisible = true
            nav_view.menu.findItem(R.id.nav_patient_data).isVisible = false
            nav_view.menu.findItem(R.id.nav_feedback).isVisible = false
            nav_view.menu.findItem(R.id.nav_calendar).isVisible = false
            nav_view.menu.findItem(R.id.nav_chat).isVisible = false
            nav_view.menu.findItem(R.id.nav_notifications).isVisible = false

            //A opción de configuración estará desactivada por agora ata a súa implementación
            nav_view.menu.findItem(R.id.nav_manage).isVisible = false
            nav_view.menu.findItem(R.id.nav_log_out).isVisible = false
        }
    }

    //Función para controlar o pulsado do botón atras do teléfono que non deixando voltar á actividade anterior
    //ou pechando o menu en caso de que estea aberto
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //Control da selección do menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_log_in -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_patient_data -> {
                val intent = Intent(this, PatientDataActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_feedback -> {
                val intent = Intent(this, ListaFeedbacksActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_chat -> {
                val intent = Intent(this, ChatActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_calendar -> {
                val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_notifications -> {
                val intent = Intent(this, NotificationsActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_manage -> {

            }

            R.id.nav_log_out -> {
                val intent = Intent(this, LogoutActivity::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

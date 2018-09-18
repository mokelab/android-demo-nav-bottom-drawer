package com.mokelab.navbottomdrawer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var appBar: AppBarLayout
    private lateinit var bottomNavigation: BottomNavigationView
    private var toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        this.drawer = findViewById(R.id.drawer_layout)
        this.appBar = findViewById(R.id.appbar)
        this.bottomNavigation = findViewById(R.id.bottom_nav)

        val navController = Navigation.findNavController(this, R.id.container)
        NavigationUI.setupWithNavController(toolbar, navController)
        NavigationUI.setupWithNavController(this.bottomNavigation, navController)

        navController.addOnNavigatedListener { _, destination ->
            val flag = showHome(destination)
            supportActionBar?.setDisplayShowHomeEnabled(flag)
            supportActionBar?.setDisplayHomeAsUpEnabled(flag)
        }
    }

    private fun showHome(destination: NavDestination) = when (destination.id) {
        R.id.splashFragment -> false
        R.id.homeFragment -> false
        else -> true
    }

    var drawerEnabled
        get() = drawer.getDrawerLockMode(drawer) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        set(value) {
            drawer.setDrawerLockMode(if (value) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            if (value) {
                toggle = ActionBarDrawerToggle(this, drawer, findViewById(R.id.toolbar), R.string.app_name, R.string.app_name)
                toggle?.syncState()
            }
        }

    var appBarVisible
        get() = appBar.visibility == View.VISIBLE
        set(value) {
            appBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    var bottomNavVisible
        get() = bottomNavigation.visibility == View.VISIBLE
        set(value) {
            bottomNavigation.visibility = if (value) View.VISIBLE else View.GONE
        }

}

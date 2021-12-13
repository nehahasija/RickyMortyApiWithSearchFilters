package com.rickymorty.androiddemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.rickymorty.androiddemo.R

class MainActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AndroidDemo)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(navController!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp() || super.onSupportNavigateUp()
    }

}
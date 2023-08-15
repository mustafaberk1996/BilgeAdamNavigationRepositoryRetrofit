package com.example.navigationwithtoolbartitlelesson.ui.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.navigationwithtoolbartitlelesson.R
import com.example.navigationwithtoolbartitlelesson.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController


        binding.navView.setupWithNavController(navController)




    }
}
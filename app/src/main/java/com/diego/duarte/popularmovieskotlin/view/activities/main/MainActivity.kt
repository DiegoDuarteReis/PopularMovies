package com.diego.duarte.popularmovieskotlin.view.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diego.duarte.popularmovieskotlin.R
import com.diego.duarte.popularmovieskotlin.view.fragments.movies.MoviesFragment

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MoviesFragment()
        fragmentTransaction.add(R.id.main_fragment, fragment)
        fragmentTransaction.commit()
    }


}
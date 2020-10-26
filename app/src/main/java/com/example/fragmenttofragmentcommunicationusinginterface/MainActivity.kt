package com.example.fragmenttofragmentcommunicationusinginterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() ,FragmentA.FragAlistener,FragmentB.FragBlistener{

    val fragmentA = FragmentA()
    val fragmentB= FragmentB()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragABundle=Bundle()
        fragABundle.putString("attach message","Fragment A is attached")
        fragmentA.arguments=fragABundle

        val fragBBundle=Bundle()
        fragBBundle.putString("attach message","Fragment B is attached")
        fragmentB.arguments=fragBBundle



        val frgManager = supportFragmentManager
        val fragTransaction= frgManager.beginTransaction()
        fragTransaction.add(R.id.container_a,fragmentA)
        fragTransaction.add(R.id.container_b,fragmentB)
        fragTransaction.commit()
    }

    override fun onInputASent(input: String) {
        fragmentB.updateEditText(input)

    }

    override fun onInputBSent(input: String) {
        fragmentA.updateEditText(input)
    }
}
package com.example.duastelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var btnGoToTela2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoToTela2 = findViewById(R.id.btnGoToTela1)
        btnGoToTela2.setOnClickListener{ it: View
            val intent = Intent(this, tela2::class.java)
            startActivity(intent)
    }
}
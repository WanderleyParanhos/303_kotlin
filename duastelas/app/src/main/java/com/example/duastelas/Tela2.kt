package com.example.duastelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Tela2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        val btnVoltar = findViewById(R.id.btnVoltar) as Button
        btnVoltar.setOnClickListener{
            this.finish()
        }
    }
}
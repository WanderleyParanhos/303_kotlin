package com.example.duastelasretorno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d("Teste", "Retorno")
            Log.d("Teste", result.resultCode.toString())
            if(result.resultCode == Actvity_RESULT_OK){
                val data: Intent? = result.data
                val txt = findViewById(R.id.textView) as TextView
                val msg = data?.extras.getStringExtra("ActivityResult")
                txt.SetText(msg)
            }

        val btnNext = findViewById(R.id.btnGetValor) as Button
        btnNext.SetOnClickListener{
            val intent = Intent(this, TelaValores::class.java)
            laucher.launch(intent)
        }
    }
    }
}
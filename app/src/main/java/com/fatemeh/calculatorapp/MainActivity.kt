package com.fatemeh.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric:Boolean=false
    var lasDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        txtInput.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        txtInput.text=""
        lastNumeric=false
        lasDot=false
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lasDot){
            txtInput.append(".")
            lastNumeric=false
            lasDot=true

        }
    }
}
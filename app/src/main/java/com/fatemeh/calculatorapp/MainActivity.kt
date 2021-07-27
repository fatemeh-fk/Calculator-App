package com.fatemeh.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

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
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(txtInput.text.toString())){
            txtInput.append((view as Button).text)
            lastNumeric=false
            lasDot=false
        }
    }
    fun onEqeal(view: View){
        if(lastNumeric){
            var tvValue=txtInput.text.toString()
            var prefix=""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    //99/1
                    var left=splitValue[0]//99
                    var right=splitValue[1]//1

                    if(!prefix.isEmpty()){
                        left=prefix+left

                    }
                    txtInput.text=removeZeroAfterDot((left.toDouble()/right.toDouble()).toString())
                }
                if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    //99*1
                    var left=splitValue[0]//99
                    var right=splitValue[1]//1

                    if(!prefix.isEmpty()){
                        left=prefix+left

                    }
                    txtInput.text=removeZeroAfterDot((left.toDouble()*right.toDouble()).toString())
                }
                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    //99-1
                    var left=splitValue[0]//99
                    var right=splitValue[1]//1

                    if(!prefix.isEmpty()){
                        left=prefix+left

                    }
                    txtInput.text=removeZeroAfterDot((left.toDouble()-right.toDouble()).toString())
                }
                if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    //99-1
                    var left=splitValue[0]//99
                    var right=splitValue[1]//1

                    if(!prefix.isEmpty()){
                        left=prefix+left

                    }
                    txtInput.text=removeZeroAfterDot((left.toDouble()+right.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(result:String):String{
        var value=result
        if(result.contains("0"))
            value=result.substring(0,result.length-2);
            return value

    }
    private fun isOperatorAdded(value :String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||value.contains("*")
                    ||value.contains("+")||value.contains("-")
        }
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lasDot){
            txtInput.append(".")
            lastNumeric=false
            lasDot=true

        }
    }
}
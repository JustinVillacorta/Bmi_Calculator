package com.example.bmicalcuapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weightText= findViewById<EditText>(R.id.etWeight)
        val heightText= findViewById<EditText>(R.id.etHeight)
        val calcButton= findViewById<Button>(R.id.BtnCalculate)

        calcButton.setOnClickListener{
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }


        }


    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()-> {
                Toast.makeText(this, "height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }



    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvinfo)


        resultIndex.text = bmi.toString()
        info.text = "(normal Range is 18.5 to 24.9)"
        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText ="Underweight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color =R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText ="overweighht"
                color = R.color.over_Weight
            }
            bmi > 29.99 -> {
                resultText="Obese"
                color = R.color.obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText



    }
}
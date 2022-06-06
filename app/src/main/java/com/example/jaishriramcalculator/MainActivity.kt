package com.example.jaishriramcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operator) }
    private var operand1: Double? = null
    private var pendingOperation = "="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.editTextNumberSigned2)
        newNumber = findViewById(R.id.newnumber)
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val dot = findViewById<Button>(R.id.dot)
        val equals = findViewById<Button>(R.id.equal)
        val divide = findViewById<Button>(R.id.Divide)
        val multiply = findViewById<Button>(R.id.multiply)
        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)

        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        dot.setOnClickListener(listener)

        val oplistener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            }
            catch (e:NumberFormatException)
            {
                newNumber.setText("")
            }

            pendingOperation = op
            displayOperation.text = pendingOperation
        }
        equals.setOnClickListener(oplistener)
        multiply.setOnClickListener(oplistener)
        divide.setOnClickListener(oplistener)
        plus.setOnClickListener(oplistener)

        minus.setOnClickListener(oplistener)

    }
    private  fun performOperation(value:Double,op:String)
    {
        if(operand1==null)
        {
            operand1=value
        }
        else {


            if (pendingOperation == "=") {
                pendingOperation = op
            }
            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "+" -> operand1 = operand1!! + value
                "-" -> operand1 = operand1!! - value
            }
        }
    result.setText(operand1.toString())
    newNumber.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}





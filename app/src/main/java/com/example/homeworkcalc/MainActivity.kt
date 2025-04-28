package com.example.homeworkcalc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0
    private var secondOperand = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultsTV)

        val buttons = listOf(
            R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button0, R.id.buttonDot, R.id.buttonPlus,
            R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonEqual, R.id.buttonDel
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button0 -> appendInput("0")
            R.id.button1 -> appendInput("1")
            R.id.button2 -> appendInput("2")
            R.id.button3 -> appendInput("3")
            R.id.button4 -> appendInput("4")
            R.id.button5 -> appendInput("5")
            R.id.button6 -> appendInput("6")
            R.id.button7 -> appendInput("7")
            R.id.button8 -> appendInput("8")
            R.id.button9 -> appendInput("9")
            R.id.buttonDot -> appendInput(".")
            R.id.buttonPlus -> setOperator("+")
            R.id.buttonMinus -> setOperator("-")
            R.id.buttonMultiply -> setOperator("*")
            R.id.buttonDivide -> setOperator("/")
            R.id.buttonEqual -> calculateResult()
            R.id.buttonDel -> clear()
        }
    }

    private fun appendInput(value: String) {
        currentInput += value
        resultTextView.text = currentInput
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty()) {
            secondOperand = currentInput.toDouble()
            when (operator) {
                "+" -> currentInput = (firstOperand + secondOperand).toString()
                "-" -> currentInput = (firstOperand - secondOperand).toString()
                "*" -> currentInput = (firstOperand * secondOperand).toString()
                "/" -> {
                    if (secondOperand != 0.0) {
                        currentInput = (firstOperand / secondOperand).toString()
                    } else {
                        currentInput = "Error"
                    }
                }
            }
            resultTextView.text = currentInput
            operator = ""
            firstOperand = 0.0
            secondOperand = 0.0
        }
    }

    private fun clear() {
        currentInput = ""
        operator = ""
        firstOperand = 0.0
        secondOperand = 0.0
        resultTextView.text = "0"
    }
}

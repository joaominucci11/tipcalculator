package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorApp()
        }
    }
}

@Composable
fun TipCalculatorApp() {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val tipAmount = calculateTip(billAmount, tipPercentage, roundUp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Calculate Tip", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = billAmount,
            onValueChange = { billAmount = it },
            label = { Text("Bill Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            label = { Text("Tip Percentage") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){}

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tip Amount: $tipAmount",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

fun calculateTip(bill: String, tip: String, roundUp: Boolean): String {
    val billAmount = bill.toDoubleOrNull() ?: 0.0
    val tipPercent = tip.toDoubleOrNull() ?: 0.0

    var tipAmount = billAmount * (tipPercent / 100)
    if (roundUp) {
        tipAmount = ceil(tipAmount)
    }

    return NumberFormat.getCurrencyInstance().format(tipAmount)
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalculatorApp()
}
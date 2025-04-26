package com.countrydelight.cdfuncttionhelperproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.countrydelight.cdfunctionhelper.getPreviousMonthName
import com.countrydelight.cdfuncttionhelperproject.ui.theme.CDFuncttionHelperProjectTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CDFuncttionHelperProjectTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) { innerPadding ->
                    Greeting(
                        name = null,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String?, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val calender = Calendar.getInstance()
        calender.add(Calendar.DAY_OF_MONTH, -1)
        Text(getPreviousMonthName().toString())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CDFuncttionHelperProjectTheme {
        Greeting("Android")
    }
}
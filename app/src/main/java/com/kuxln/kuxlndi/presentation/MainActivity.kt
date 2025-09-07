package com.kuxln.kuxlndi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuxln.kuxlndi.di

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by di()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                val bigText by remember {
                    mutableStateOf(
                        viewModel.getStringsList().fold("") { acc, newElement ->
                            "$acc $newElement"
                        }
                    )
                }

                Column(modifier = Modifier.padding(innerPadding)) {
                    Text(text = viewModel.getString())

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = bigText)
                }
            }
        }
    }
}

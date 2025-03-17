package com.seasonin.lazycolumnlistwithanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seasonin.lazycolumnlistwithanimation.ui.theme.LazycolumnlistwithanimationTheme

class MainActivity : ComponentActivity() {
    private val animals = listOf(
        "Lion", "Tiger", " Elephant", "Giraffe",
        "Starfish" +
                "JellyFish",
        "Eagle", "Dolphin", "Shark", "Whale", "Walrus", "Lion", "Tiger", " Elephant", "Giraffe",
        "Starfish" +
                "JellyFish",
        "Eagle", "Dolphin", "Shark", "Whale", "Walrus",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazycolumnlistwithanimationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   AnimalList(animals = animals, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AnimalRow(animal: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = animal, Modifier
            .fillMaxSize()
            .padding(16.dp))
    }
}

@Composable
fun AnimalList(animals: List<String>, modifier: Modifier) {
    LazyColumn {
        items(animals) { animal ->
            AnimalRow(animal = animal)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazycolumnlistwithanimationTheme {
        Greeting("Android")
    }
}
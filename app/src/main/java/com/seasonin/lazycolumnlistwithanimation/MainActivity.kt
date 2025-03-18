package com.seasonin.lazycolumnlistwithanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seasonin.lazycolumnlistwithanimation.ui.theme.LazycolumnlistwithanimationTheme

class MainActivity : ComponentActivity() {
    private val animals = listOf(
        "Lion", "Tiger", "Elephant", "Giraffe", "Zebra",
        "Kangaroo", "Panda", "Bear", "Monkey", "Gorilla",
        "Cheetah", "Leopard", "Rhinoceros", "Hippopotamus", "Crocodile",
        "Alligator", "Ostrich", "Eagle", "Penguin", "Dolphin",
        "Whale", "Shark", "Octopus", "Jellyfish", "Starfish"
    )
    private val animalColors = mapOf(
        "Lion" to Color.Yellow,
        "Tiger" to Color(0xFFFFA500), // Orange
        "Elephant" to Color.Gray,
        "Giraffe" to Color(0xFFA52A2A), // Brown
        "Zebra" to Color.LightGray,
        "Kangaroo" to Color(0xFFA52A2A), // Brown
        "Panda" to Color.LightGray,
        "Bear" to Color(0xFFA52A2A), // Brown
        "Monkey" to Color(0xFFA52A2A), // Brown
        "Gorilla" to Color.Magenta,
        "Cheetah" to Color.Yellow,
        "Leopard" to Color.Magenta,
        "Rhinoceros" to Color.Gray,
        "Hippopotamus" to Color.Gray,
        "Crocodile" to Color.Green,
        "Alligator" to Color.Green,
        "Ostrich" to Color(0xFFA52A2A), // Brown
        "Eagle" to Color(0xFFA52A2A), // Brown
        "Penguin" to Color.LightGray,
        "Dolphin" to Color.Gray,
        "Whale" to Color.Blue,
        "Shark" to Color.Gray,
        "Octopus" to Color(0xFF800080), // Purple
        "Jellyfish" to Color(0xFFFFC0CB), // Pink
        "Starfish" to Color(0xFFFFA500) // Orange
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LazycolumnlistwithanimationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController, startDestination = "animal_list") {
                        composable("animal_list") {
                            AnimalList(animals = animals,navController, modifier = Modifier.padding(innerPadding))
                        }
                        composable("animal_details/{animal}") { navBackStackEntry ->
                            val animal = navBackStackEntry.arguments?.getString("animal")
                            if(animal != null) {
                                AnimalDetails(animal)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimalRow(animal: String, navController: NavHostController) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("Click me") }
    Box(modifier = Modifier.fillMaxSize()
        .clickable {
            navController.navigate("animal_details/$animal")
        }) {
        Text(text = animal, Modifier
            .fillMaxSize()
            .padding(16.dp))

    }
}

@Composable
fun AnimalDetails(animal: String) {
    Column {
        Text(text = "Detail page for $animal")
    }
}

@Composable
fun AnimalList(animals: List<String>, navController: NavHostController, modifier: Modifier) {
    LazyColumn {
        items(animals) { animal ->
            AnimalRow(animal = animal, navController)
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
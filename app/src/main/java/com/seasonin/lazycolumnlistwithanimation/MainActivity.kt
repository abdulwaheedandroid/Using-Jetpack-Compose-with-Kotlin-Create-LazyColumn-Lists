package com.seasonin.lazycolumnlistwithanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
                            AnimalListSlideIn(
                                animals = animalColors,
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable("animal_details/{animal}") { navBackStackEntry ->
                            val animal = navBackStackEntry.arguments?.getString("animal")
                            val color = animalColors[animal]?: Color.White
                            if (animal != null) {
                                AnimalDetails(animal, color)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimalRow(animal: String,color: Color? = null, navController: NavHostController) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("Click me") }
    val animalColor = color ?: Color.LightGray
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            navController.navigate("animal_details/$animal")
        }
        .background(animalColor)) {
        Text(
            text = animal, Modifier
                .fillMaxSize()
                .padding(16.dp)
        )

    }
}

@Composable
fun AnimalDetails(animal: String, initialColor: Color) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta)
    var currentColor by remember { mutableStateOf(initialColor) }
    val animatedColor by animateColorAsState(targetValue = currentColor, animationSpec = tween(2000))
    Column {
        Box(modifier = Modifier.fillMaxSize().background(animatedColor)) {
            Text(text = "Detail page for $animal", modifier = Modifier.align(Alignment.Center))
            Button(
                onClick = {
                    currentColor = colors.random()
                }, modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)) {
                Text("Change Color")
            }
        }
    }
}

@Composable
fun AnimalList(animals: Map<String, Color>, navController: NavHostController, modifier: Modifier) {
    LazyColumn {
        items(animals.keys.toList()) { animal ->
            AnimalRow(animal = animal, color = null ,navController)
        }
    }
}

@Composable
fun AnimalListCrossFade(animals: Map<String, Color>, navController: NavHostController, modifier: Modifier) {
    LazyColumn {
        items(animals.keys.toList()) { animal ->
            val color = Color.LightGray
            var isVisible by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = animal) {
                isVisible = true
            }

            AnimatedVisibility(visible = isVisible,
                enter = fadeIn(animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                AnimalRow(animal = animal, color = null ,navController)
            }

        }
    }
}

@Composable
fun AnimalListSlideIn(animals: Map<String, Color>, navController: NavHostController, modifier: Modifier) {
    LazyColumn {
        items(animals.keys.toList()) { animal ->
            val color = Color.LightGray
            var isVisible by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = animal) {
                isVisible = true
            }

            AnimatedVisibility(visible = isVisible,
                enter = slideInHorizontally (initialOffsetX = {-1000}, animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                AnimalRow(animal = animal, color = null ,navController)
            }

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
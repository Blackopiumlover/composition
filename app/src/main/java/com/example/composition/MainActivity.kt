package com.example.composition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composition.navigation.NavigationEvent
import com.example.composition.navigation.NavigationHandler
import com.example.composition.navigation.Screen
import com.example.composition.navigation.homeGraph
import com.example.composition.ui.theme.CompositionTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionTheme {
                val navController = rememberNavController()

                LaunchedEffect(navigationHandler) {
                    navigationHandler.navigationEvents.collect { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route)
                            }
                            is NavigationEvent.PopBackStack -> {
                                navController.popBackStack(route = event.route, inclusive = false)
                            }
                            is NavigationEvent.ExitApp -> {
                                finishAffinity()
                            }
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    homeGraph(navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}
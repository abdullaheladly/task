package com.paymob.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paymob.presentation.converter.ConverterScreen
import com.paymob.presentation.history.HistoryScreen
import com.paymob.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    MainNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Home.route) {
        composable(route = NavRoutes.Home.route) {
            ConverterScreen(modifier = Modifier, navController = navController)
        }
        composable(
            route = NavRoutes.History.route,
            arguments = NavRoutes.History.arguments
        ) {
            HistoryScreen(modifier = Modifier,NavRoutes.History.fromEntry(it))

        }
    }
}
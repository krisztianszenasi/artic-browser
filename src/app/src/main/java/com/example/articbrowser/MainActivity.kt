package com.example.articbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.articbrowser.navigation.NavGraph
import com.example.articbrowser.ui.theme.ARTICBrowserTheme
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = FirebaseAnalytics.getInstance(this)
        enableEdgeToEdge()
        setContent {
            ARTICBrowserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    setupScreenTracking(navController)
                    NavGraph(
                        navController,
                        modifier =  Modifier.padding((innerPadding))
                    )
                }
            }
        }
    }

    private fun setupScreenTracking(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val params = Bundle()
            params.putString(FirebaseAnalytics.Param.SCREEN_NAME, destination.route)
            params.putString(FirebaseAnalytics.Param.SCREEN_CLASS, destination.route)
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)
        }
    }
}
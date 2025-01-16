package com.acon.acon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.acon.acon.navigation.AconNavigation
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.repository.GoogleTokenRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var googleTokenRepository: GoogleTokenRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AconTheme {
                AconApp(googleTokenRepository)
            }
        }
    }
}

@Composable
fun AconApp(
    googleTokenRepository: GoogleTokenRepository,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        AconNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            googleTokenRepository = googleTokenRepository,
        )
    }
}

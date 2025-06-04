package com.example.articbrowser.feature.artic_about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articbrowser.R
import com.example.articbrowser.feature.art_grid.ArtGridScreen
import com.example.articbrowser.ui.theme.Typography
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = R.drawable.aic_logo),
                contentDescription = "Image Description",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        throw RuntimeException("Test Crash") // Force a crash
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Founded in 1879, the Art Institute of Chicago is one of the world’s major museums, housing an extraordinary collection of objects from across places, cultures, and time. We are also a place of active learning for all—dedicated to investigation, innovation, education, and dialogue—continually aspiring to greater public service and civic engagement.",
                style = Typography.bodyLarge
            )
        }
    }
}


@Preview
@Composable
fun ArtDetailsScreenPreview() {
    Box(Modifier.background(Color.Yellow)) {
        AboutScreen()
    }
}
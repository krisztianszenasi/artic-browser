package com.example.articbrowser.feature.art_details

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.articbrowser.data.ResponseWrapper
import com.example.articbrowser.domain.Art
import com.example.articbrowser.feature.art_details.molecules.ArtAttributeCard
import com.example.articbrowser.feature.art_grid.ArtItem
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun ArtDetailsScreen(
    artId: Int,
    viewModel: ArtDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var artResponse by remember { mutableStateOf<ResponseWrapper<Art>?>(null) }
    val scrollState = rememberScrollState()

    LaunchedEffect(artId) {
        artResponse = viewModel.fetchArtDetail(artId)

        if(artResponse != null && !artResponse!!.isSuccessful) {
            Toast.makeText(
                context,
                "Error: " + (artResponse!!.exception!!.message),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if(artResponse == null) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        artResponse?.data?.let {art ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                ArtItem(
                    art = art,
                    cacheKeyPrefix = "image_large",
                    displayFullText = true,
                    useDisplayTexts = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                DisplayAttributeIfPresent(title = "description", text = art.description)
                DisplayAttributeIfPresent(title = "publication history", text = art.publicationHistory)
                DisplayAttributeIfPresent(title = "exhibition history", text = art.exhibitionHistory)
            }
        }
    }
}


@Composable
fun DisplayAttributeIfPresent(title: String, text: String?) {
    if(text != null) {
        ArtAttributeCard(title = title, text = text)
        Spacer(modifier = Modifier.height(8.dp))
    }
}



@Preview
@Composable
fun ArtDetailsScreenPreview() {
    Box(Modifier.background(Color.Yellow)) {
//        ArtDetailsScreen(123)
    }
}
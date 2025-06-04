package com.example.articbrowser.feature.art_grid

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.articbrowser.R
import com.google.firebase.analytics.FirebaseAnalytics


@Composable
fun ArtGridScreen(
    viewModel: ArtGridViewModel = hiltViewModel(),
    onLogoClick: () -> Unit,
    onArtClick: (Int) -> Unit
) {
    val arts = viewModel.artPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = arts.loadState) {
        if(arts.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (arts.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.aic_logo),
                contentDescription = "Image Description",
                modifier = Modifier
                    .height(48.dp)
                    .clickable { onLogoClick() },
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.width(4.dp))

            SearchBar(
                query = query,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {newQuery ->
                query = newQuery
                viewModel.search(newQuery)
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if(arts.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(arts.itemCount) { index ->
                            val art = arts[index]
                            if(art != null) {
                                ArtItem(
                                    art = art,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .clickable { onArtClick(art.id) }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}



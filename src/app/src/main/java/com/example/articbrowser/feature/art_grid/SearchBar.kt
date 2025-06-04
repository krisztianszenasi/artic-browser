package com.example.articbrowser.feature.art_grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articbrowser.ui.theme.ARTICBrowserTheme

@Composable
fun SearchBar(
    searchLabel: String = "Search Art",
    modifier: Modifier = Modifier,
    query: String, onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Search Art") },
        modifier = modifier
    )
}


@Preview
@Composable
fun SearchBarPreview() {
    ARTICBrowserTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp)) {
            SearchBar(query = "") {}
        }
   }
}
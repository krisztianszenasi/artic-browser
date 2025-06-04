package com.example.articbrowser.feature.art_details.molecules

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articbrowser.ui.theme.ARTICBrowserTheme
import com.example.articbrowser.ui.theme.Typography


@Composable
fun ArtAttributeCard(title: String, text: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize()

        ) {
            Text(
                text = title,
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                text = text,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
fun PreviewArtAttributeCard() {
    ARTICBrowserTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            ArtAttributeCard(
                title = "Description",
                text = "<p>After decades as a representational painter, in her seventies Alma Thomas turned to abstraction, creating shimmering, mosaic-like fields of color with rhythmic dabs of paint that were often inspired by forms from nature. The artist had been fascinated with space exploration since the late 1960s, and her later paintings often referenced America’s manned Apollo missions to the moon. Although she had never flown, Thomas began to paint as if she were in an airplane, capturing what she described as shifting patterns of light and streaks of color. “You look down on things,” she explained. “You streak through the clouds so fast. . . . You see only streaks of color.”</p>\\n<p><em>Starry Night and the Astronauts</em> evokes the open expanse and celestial patterns of a night sky, but despite its narrative title, the work could also be read as an aerial view of a watery surface, playing with our sense of immersion within an otherwise flat picture plane. The viewer is immersed not only in the sense of organic expanse that this painting achieves, however, but also in an encounter with Thomas’s process: the surface here is clearly constructed stroke by stroke. Meanwhile, the glimpses of raw canvas between each primary-colored mark seem as vivid as the applied paint itself—almost as if the composition were backlit. Thomas relied on the enlivening properties of color throughout her late-blooming career. “Color is life,” she once proclaimed, “and light is the mother of color.” This painting was created in 1972, when the artist was eighty. In the same year, she became the first African American woman to receive a solo exhibition at a major art museum, the Whitney Museum of American Art in New York City.</p>\\n"
            )
        }
    }
}
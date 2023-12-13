package com.anil.moviesapp.features.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.anil.moviesapp.R
import com.anil.moviesapp.data.model.Actors
import com.anil.moviesapp.data.model.Reviews
import com.anil.moviesapp.util.Constants


/*Movie Actor's details Card*/
@Composable
fun actorsCard(actor: Actors) {
    Box {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val actorImg =
                rememberAsyncImagePainter(model = Constants.IMAGE_URL + actor.profilePath)

            Image(
                painter = actorImg,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = actor.character,
                modifier = Modifier.width(120.dp),
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = actor.originalName,
                modifier = Modifier.width(120.dp),
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun reviewCard(reviews: Reviews) {
    Card(Modifier.padding(4.dp)) {
        Column {
            Text(
                text = reviews.content,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "- ${reviews.author}",
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = TextStyle(textAlign = TextAlign.End, fontWeight = FontWeight.Bold)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun actorPreviewCard() {
    Column(
        modifier = Modifier
            .width(120.dp)
            .height(150.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tomcrus),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = stringResource(R.string.sample_text),
            modifier = Modifier,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = stringResource(R.string.sample_text),
            style = TextStyle(fontSize = 12.sp)
        )
    }
}
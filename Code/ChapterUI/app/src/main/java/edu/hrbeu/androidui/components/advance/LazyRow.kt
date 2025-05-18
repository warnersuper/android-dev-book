package edu.hrbeu.androidui.components.advance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.R

@Preview(showBackground = true)
@Composable
fun LazyRowDemo1() {
    val letters  = listOf("A", "B", "C", "D", "E","F","G","H","I","J","K","L","M")
    LazyRow {
        itemsIndexed(letters) { index, item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LazyRowDemo2() {
    val imageList = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(imageList) { index, imageRes ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}
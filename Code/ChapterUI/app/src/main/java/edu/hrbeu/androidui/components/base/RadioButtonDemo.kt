package edu.hrbeu.androidui.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme


@Preview(showBackground = true)
@Composable
fun RadioButtonDemo1() {
    var selectedOption by remember { mutableStateOf("Male") }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == "Male",
                onClick = { selectedOption = "Male" }
            )
            Text("Male")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == "Female",
                onClick = { selectedOption = "Female" }
            )
            Text("Female")
        }

        Text("Selected: $selectedOption")
    }
}



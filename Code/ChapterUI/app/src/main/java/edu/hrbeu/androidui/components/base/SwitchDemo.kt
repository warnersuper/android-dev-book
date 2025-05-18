package edu.hrbeu.androidui.components.base


import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.hrbeu.androidui.ui.theme.AndroidUIDemoTheme



@Preview(showBackground = true)
@Composable
fun SwitchDemo1() {
    var checkedState by remember { mutableStateOf(true) }
    Switch(
        checked = checkedState,
        onCheckedChange = { checkedState = it }
    )
}

@Preview(showBackground = true)
@Composable
fun SwitchDemo2() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = { checked = it },
        thumbContent = {
            if (checked) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        }
    )
}


package edu.hrbeu.chapterjetpack.demo1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenC(
    navigateToB:()->Unit,
    navigateToA:()->Unit
){
    Scaffold (
        topBar = {
            TopAppBar( { Text("Screen C") } )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = navigateToB
            ){
                Text("Back to B")
            }
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = navigateToA
            ){
                Text("Back to A")
            }
        }
    }
}

@Preview
@Composable
fun ScreenCPre(){
    ScreenC({}, {})
}
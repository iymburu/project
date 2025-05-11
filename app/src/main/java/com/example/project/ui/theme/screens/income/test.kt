package com.example.project.ui.theme.screens.income

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.project.R

@Composable
fun Test(modifier: Modifier = Modifier) {

    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (topBar,namerow,list,bottom) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_topbar), contentDescription = "bar",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            Box(modifier =Modifier
                .padding(top = 94.dp, start = 26.dp, end = 16.dp)
                .fillMaxWidth()
                .constrainAs(namerow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }){
                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(" Profile "+
                            "",fontSize = 30.sp, fontWeight = FontWeight.Medium, color =  Color.White,
                        fontStyle = FontStyle.Normal
                    )

                }}
            Column(modifier = Modifier.constrainAs(list){
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {

            }
        }}
}

@Preview
@Composable
private fun Test_prev() {
    Test()

}
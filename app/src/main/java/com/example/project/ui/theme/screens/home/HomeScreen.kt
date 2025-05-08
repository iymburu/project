package com.example.project.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.ui.theme.myblue

@Composable
fun Homescreen(navController: NavHostController) {

    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (topBar,card,namerow,list,add,bottom) = createRefs()
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
               .padding(top = 64.dp, start = 26.dp, end = 16.dp)
               .fillMaxWidth()
               .constrainAs(namerow) {
                   top.linkTo(parent.top)
                   start.linkTo(parent.start)
                   end.linkTo(parent.end)
               }){
               Column(modifier = Modifier.align(Alignment.CenterStart)) {
               Text("Welcome"+
                       "",fontSize = 30.sp, fontWeight = FontWeight.Medium, color =  Color.White,
                   fontStyle = FontStyle.Normal
               )

               }
               Image(
                   painter = painterResource(id = R.drawable.vert_menu), contentDescription ="menu", modifier = Modifier.align(Alignment.TopEnd)
               , colorFilter = ColorFilter.tint(color = Color.White))
           }

            Column(modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(namerow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 84.dp, start = 26.dp, end = 16.dp)
                .shadow(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(color = myblue, shape = RoundedCornerShape(16.dp))
                .clip(shape = RoundedCornerShape(16.dp))) {
                Box(modifier = Modifier.padding(top = 17.dp, start = 10.dp, end = 4.dp)){
                    Text("Current Balance", fontSize = 20.sp, fontWeight = FontWeight.Medium , color = Color.White)

                }
            }
            Column(modifier= Modifier.constrainAs(list){
                top.linkTo(card.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .height(400.dp)) {  }


        }}

}





@Preview
@Composable
private fun Homescreen_Preview() {
    Homescreen(rememberNavController())

}
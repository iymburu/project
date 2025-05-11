package com.example.project.ui.theme.screens.home

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.data.TransactionModel
import com.example.project.navigation.ROUTE_ADD
import com.example.project.navigation.ROUTE_ADD_INCOME
import com.example.project.navigation.ROUTE_CHART
import com.example.project.navigation.ROUTE_HOME
import com.example.project.navigation.ROUTE_VIEW
import com.example.project.ui.theme.myblue
import com.example.project.ui.theme.white
import com.google.android.play.core.integrity.p

@Composable
fun Homescreen(navController: NavHostController,

)
{




    var expanded by remember { mutableStateOf(false) }


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
                   painter = painterResource(id = R.drawable.account), contentDescription ="menu", modifier = Modifier.align(Alignment.TopEnd).clickable { navController.navigate(ROUTE_VIEW) }
               , colorFilter = ColorFilter.tint(color = Color.White))
           }


            Column(modifier= Modifier.padding(start = 20.dp,end=20.dp)
                .constrainAs(list) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(450.dp)) {
                Spacer(modifier = Modifier.height(60.dp))
                Button({ROUTE_ADD_INCOME}, modifier = Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(shape = RectangleShape, color = myblue),
                    colors = ButtonDefaults.buttonColors(myblue),
                ) {
                    Text("Add Income", fontSize = 20.sp, color =Color.White , modifier = Modifier.align(Alignment.CenterVertically))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button({navController.navigate(ROUTE_ADD)}, modifier = Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(shape = RectangleShape, color = myblue),
                    colors = ButtonDefaults.buttonColors(myblue),
                ) {
                    Text("Add Expense", fontSize = 20.sp,color =Color.White, modifier = Modifier.align(Alignment.CenterVertically))
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button({navController.navigate(ROUTE_VIEW)}, modifier = Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(shape = RectangleShape, color = myblue),
                    colors = ButtonDefaults.buttonColors(myblue),
                ) {
                    Text("View Transactions", fontSize = 20.sp,color =Color.White, modifier = Modifier.align(Alignment.CenterVertically))
                }
                Spacer(modifier = Modifier.height(20.dp))


            }

            Box( modifier = Modifier
                .constrainAs(add) {
                    top.linkTo(list.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(end = 20.dp)
                ) {
                Column(modifier = Modifier.align (Alignment.CenterEnd)) {


                }

            }
            Box( modifier = Modifier
                .constrainAs(bottom) {
                    top.linkTo(add.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 30.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(15.dp))
                )
                {Box(modifier = Modifier
                    .padding(bottom = 54.dp)
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(color = Color.Blue, shape = RoundedCornerShape(6.dp))) {
                    Column(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 54.dp) ){
                            Image(painter = painterResource(id=R.drawable.home), contentDescription = "home", colorFilter = ColorFilter.tint(color = Color.White), modifier = Modifier.clickable{navController.navigate(
                                ROUTE_HOME
                            )})}
                    Column(modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 54.dp) ){
                        Image(painter = painterResource(id=R.drawable.graph_24), contentDescription = "graph", colorFilter = ColorFilter.tint(color = Color.White), modifier = Modifier.clickable { navController.navigate(
                            ROUTE_CHART) })
                        }}


            }
        }}

}
@Preview
@Composable
private fun Home_Screen(){
    Homescreen(rememberNavController())
}





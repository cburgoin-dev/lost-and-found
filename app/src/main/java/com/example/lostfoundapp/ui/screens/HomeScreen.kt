package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.R
import com.example.lostfoundapp.ui.components.ItemCard
import com.example.lostfoundapp.ui.components.SquareButton
import com.example.lostfoundapp.ui.theme.components.Roboto

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3AB8BE))
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Hello, John",
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Did you find or lose something?",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 38.dp,
                        topEnd = 38.dp
                    )
                )
                .background(Color.White)
                .padding(18.dp)
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                SquareButton(
                    text = "Lost something",
                    bgColor = Color(0xFF3AB8BE),
                    icon = painterResource(R.drawable.ic_launcher_foreground),
                    onClick = {},
                    modifier = Modifier.width(150.dp)
                )

                Spacer(modifier= Modifier.width(10.dp))

                SquareButton(
                    text = "Found something",
                    bgColor = Color(0xFF8E8E8E),
                    icon = painterResource(R.drawable.ic_launcher_foreground),
                    onClick = {},
                    modifier = Modifier.width(150.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "RECENT",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))


            ItemCard(
                descriptionTop = "Description Top",
                title = "Title",
                descriptionBottom = "Description Bottom",
                status = "Lost",
                statusColor = Color(0xFFFFE033),
                icon = painterResource(R.drawable.ic_launcher_foreground),
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))


            ItemCard(
                descriptionTop = "Description Top",
                title = "Title",
                descriptionBottom = "Description Bottom",
                status = "Found",
                statusColor = Color(0xFF3AB8BE),
                icon = painterResource(R.drawable.ic_launcher_foreground),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}
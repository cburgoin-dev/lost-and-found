package com.example.lostfoundapp.ui.theme.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.text.isEmpty


@Composable
fun CustomInput(
    value: String,
    placeholder: String,
    onValueChange:(String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .border(1.dp, Color.LightGray,RoundedCornerShape(8.dp))
                        .padding( horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    if(value.isEmpty()){
                        BasicText(
                            text = placeholder,
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 20.sp
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )



    }
}

@Preview(showBackground = true)
@Composable
fun CustomInputPreview(){
    CustomInput( "Emiliano","Campo", onValueChange = {})
}

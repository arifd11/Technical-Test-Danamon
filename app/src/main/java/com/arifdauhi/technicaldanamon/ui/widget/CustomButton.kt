package com.arifdauhi.technicaldanamon.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arifdauhi.technicaldanamon.ui.theme.manropeFamily

@Composable
fun SubmitButton(teks: String, onClick: () -> Unit, paddingValues: PaddingValues, color: Color) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(56.dp)
            .clip(
                shape = RoundedCornerShape(5.dp)
            )
            .background(color)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            teks,
            fontFamily = manropeFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = Color.White
        )
    }
}
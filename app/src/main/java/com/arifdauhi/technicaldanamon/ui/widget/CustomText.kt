package com.arifdauhi.technicaldanamon.ui.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.arifdauhi.technicaldanamon.ui.theme.TitleTextColor
import com.arifdauhi.technicaldanamon.ui.theme.manropeFamily

@Composable
fun Title(teks: String, modifier: Modifier) {
    Text(
        teks,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = TitleTextColor
    )
}

@Composable
fun SignText(modifier: Modifier) {
    Text(
        "Belum punya akun?",
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = Color(0XFF999EA1)
    )
}

@Composable
fun SignUpText(modifier: Modifier) {
    Text(
        "Daftar",
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color(0XFF160062)
    )
}

@Composable
fun SpinnerTitleText(teks: String, modifier: Modifier) {
    Text(
        teks,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = TitleTextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LargeTitle(teks: String, modifier: Modifier) {
    Text(
        teks,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.sp,
        color = Color.Black
    )
}

@Composable
fun UserItemTextTitle(text: String, modifier: Modifier) {
    Text(
        text,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = Color(0XFF1F1F1F)
    )
}

@Composable
fun AlbumItemTextTitle(text: String, modifier: Modifier) {
    Text(
        text,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.SemiBold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        fontSize = 14.sp,
        color = Color(0XFF1F1F1F)
    )
}

@Composable
fun UserItemText(text: String, modifier: Modifier) {
    Text(
        text,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color(0XFF999EA1)
    )
}

@Composable
fun AlbumIdItemText(text: String, modifier: Modifier) {
    Text(
        text,
        modifier = modifier,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        color = Color(0XFF999EA1)
    )
}

fun annotatedString(url: String) = buildAnnotatedString {
    append("Url: ")
    append(" ")
    val start = length
    val end = length + url.length
    addStringAnnotation(tag = "terms", annotation = "", start = start, end = end)
    withStyle(
        style = SpanStyle(
            textDecoration = TextDecoration.Underline,
            color = Color(0xff64B5F6),
            fontFamily = manropeFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        ),
    ) {
        append(url)
    }
}

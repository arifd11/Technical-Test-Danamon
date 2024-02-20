package com.arifdauhi.technicaldanamon.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arifdauhi.technicaldanamon.R
import com.arifdauhi.technicaldanamon.ui.theme.PlaceholderColor
import com.arifdauhi.technicaldanamon.ui.theme.manropeFamily

@Composable
fun GeneralForm(email: MutableState<String>, placeholder: String) {

    OutlinedTextField(
        email.value,
        onValueChange = { email.value = it },
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .height(53.dp),
        placeholder = {
            Text(
                placeholder,
                color = PlaceholderColor,
                fontFamily = manropeFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        },
        textStyle = TextStyle(
            color = Color(0xFF1F1F1F),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F6FA),
            unfocusedContainerColor = Color(0xFFF5F6FA),
            unfocusedBorderColor = Color(0XFFC6C6C6),
            focusedBorderColor = Color(0XFFC6C6C6),
            focusedTextColor = Color(0xFF1D1617),
            cursorColor = Color(0xFF9E9EA2),
            selectionColors = TextSelectionColors(
                handleColor = Color(0xFF9E9EA2),
                backgroundColor = Color(0xFF9E9EA2)
            )
        ),
        singleLine = true
    )
}

@Composable
fun PasswordForm(password: MutableState<String>) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password.value,
        onValueChange = { password.value = it },
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .height(53.dp),
        placeholder = {
            Text(
                "Password",
                color = PlaceholderColor,
                fontFamily = manropeFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        },
        textStyle = TextStyle(
            color = Color(0xFF1F1F1F),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F6FA),
            unfocusedContainerColor = Color(0xFFF5F6FA),
            unfocusedBorderColor = Color(0XFFC6C6C6),
            focusedBorderColor = Color(0XFFC6C6C6),
            cursorColor = Color(0xFF9E9EA2),
            focusedTextColor = Color(0xFF1D1617),
            selectionColors = TextSelectionColors(
                handleColor = Color(0xFF9E9EA2),
                backgroundColor = Color(0xFF9E9EA2)
            )
        ),
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible) painterResource(R.drawable.visible) else painterResource(
                R.drawable.visibleoff)

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(
                modifier = Modifier.padding(end = 5.dp),
                onClick = {passwordVisible = !passwordVisible}){
                Icon(painter = image, description, modifier = Modifier.size(24.dp))
            }
        }
    )
}
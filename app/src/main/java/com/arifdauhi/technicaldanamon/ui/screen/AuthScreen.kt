package com.arifdauhi.technicaldanamon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.arifdauhi.technicaldanamon.R
import com.arifdauhi.technicaldanamon.navigation.graph.AuthScreen
import com.arifdauhi.technicaldanamon.navigation.graph.Graph
import com.arifdauhi.technicaldanamon.ui.theme.Blue80
import com.arifdauhi.technicaldanamon.ui.widget.GeneralForm
import com.arifdauhi.technicaldanamon.ui.widget.LargeTitle
import com.arifdauhi.technicaldanamon.ui.widget.PasswordForm
import com.arifdauhi.technicaldanamon.ui.widget.SignText
import com.arifdauhi.technicaldanamon.ui.widget.SignUpText
import com.arifdauhi.technicaldanamon.util.simpleToast
import com.arifdauhi.technicaldanamon.ui.widget.SpinnerTitleText
import com.arifdauhi.technicaldanamon.ui.widget.SubmitButton
import com.arifdauhi.technicaldanamon.ui.widget.Title
import com.arifdauhi.technicaldanamon.util.keySecret
import com.arifdauhi.technicaldanamon.viewmodel.MainViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

@Composable
fun LoginScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel
            .requestStatus
            .collect { meta ->
                simpleToast(context = context, msg = meta.message)
                if (meta.status){
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(85.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
        ){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.danamon_logo),
                contentDescription = "Logo"
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Title(
            teks = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp)
        )

        GeneralForm(email, "Email")

        Title(
            teks = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp, top = 10.dp)
        )

        PasswordForm(password = password)
        var login by remember {
            mutableStateOf(false)
        }

        SubmitButton(
            teks = "Login",
            onClick = {
                login = true
            },
            paddingValues = PaddingValues(top = 25.dp, start = 15.dp, end = 15.dp),
            Blue80
        )

        if (login){
            if (email.value.isEmpty()){
                simpleToast(context = context, msg = "Email cannot be empty")
            } else if (password.value.isEmpty()){
                simpleToast(context = context, msg = "Password cannot be empty")
            } else {
                val jsonObject = JSONObject()
                jsonObject.put("email", email.value)
                jsonObject.put("password", password.value)
                val jsonObjectString = jsonObject.toString()
                val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
                LaunchedEffect(Unit) {
                    viewModel.login(keySecret, requestBody)
                }
            }
            login = false
        }

        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp).height(55.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SignText(modifier = Modifier.padding(0.dp))
            SignUpText(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navController.navigate(AuthScreen.Register.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
            )
        }
    }
}


@Composable
fun RegisterScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val name = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val items = listOf("Normal", "Admin")
    val selectedIndex = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel
            .requestStatus
            .collect { meta ->
                simpleToast(context = context, msg = meta.message)
                if (meta.status){
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
        ){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.danamon_logo),
                contentDescription = "Logo"
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        LargeTitle(teks = "Create Account", modifier = Modifier.padding(0.dp))

        Spacer(modifier = Modifier.height(5.dp))

        Title(
            teks = "Full Name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp)
        )

        GeneralForm(name, "Full Name")

        Title(
            teks = "Email Address",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp, top = 10.dp)
        )

        GeneralForm(email, "Email")

        Title(
            teks = "Username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp, top = 10.dp)
        )

        GeneralForm(username, "Username")

        Title(
            teks = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp, top = 10.dp)
        )

        PasswordForm(password = password)

        Title(
            teks = "Role",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp, top = 10.dp)
        )

        DropdownRole(items, selectedIndex)

        var register by remember {
            mutableStateOf(false)
        }

        SubmitButton(
            teks = "Register",
            onClick = {
                register = true
            },
            paddingValues = PaddingValues(top = 25.dp, start = 15.dp, end = 15.dp),
            Blue80
        )

        if (register){
            if (name.value.isEmpty()){
                simpleToast(context = context, msg = "Full name cannot be empty")
            } else if (email.value.isEmpty()){
                simpleToast(context = context, msg = "Email address cannot be empty")
            } else if (username.value.isEmpty()){
                simpleToast(context = context, msg = "Username cannot be empty")
            } else if (password.value.isEmpty()){
                simpleToast(context = context, msg = "Password cannot be empty")
            } else {
                val jsonObject = JSONObject()
                jsonObject.put("name", name.value)
                jsonObject.put("username", username.value)
                jsonObject.put("email", email.value)
                jsonObject.put("password", password.value)
                jsonObject.put("role", items[selectedIndex.intValue].lowercase())
                val jsonObjectString = jsonObject.toString()
                val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
                println(jsonObject)
                LaunchedEffect(Unit) {
                    viewModel.register(keySecret, requestBody)
                }
            }
            register = false
        }

        SubmitButton(
            teks = "Back",
            onClick = {
                navController.navigate(AuthScreen.Login.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            paddingValues = PaddingValues(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 25.dp),
            Color.Gray
        )
    }
}

@Composable
fun DropdownRole(items: List<String>, selectedIndex: MutableIntState) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .height(50.dp)
            .background(
                Color(0xFFF5F6FA)
            ),
        contentAlignment = Alignment.Center
    ) {
        SpinnerTitleText(
            teks = items[selectedIndex.intValue],
            modifier = Modifier
                .wrapContentSize()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFF5F6FA)
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex.intValue = index
                        expanded = false
                    },
                    text = {
                        SpinnerTitleText(
                            teks = s,
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}
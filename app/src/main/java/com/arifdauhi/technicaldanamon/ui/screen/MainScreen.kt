package com.arifdauhi.technicaldanamon.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arifdauhi.technicaldanamon.R
import com.arifdauhi.technicaldanamon.model.album.Photos
import com.arifdauhi.technicaldanamon.model.user.Data
import com.arifdauhi.technicaldanamon.navigation.graph.Graph
import com.arifdauhi.technicaldanamon.ui.theme.manropeFamily
import com.arifdauhi.technicaldanamon.ui.widget.AlbumIdItemText
import com.arifdauhi.technicaldanamon.ui.widget.AlbumItemTextTitle
import com.arifdauhi.technicaldanamon.ui.widget.UserItemText
import com.arifdauhi.technicaldanamon.ui.widget.UserItemTextTitle
import com.arifdauhi.technicaldanamon.ui.widget.annotatedString
import com.arifdauhi.technicaldanamon.util.destroy
import com.arifdauhi.technicaldanamon.util.getLoginData
import com.arifdauhi.technicaldanamon.util.simpleToast
import com.arifdauhi.technicaldanamon.viewmodel.MainViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val loginData = getLoginData()

    when(loginData.role){
        "admin" -> {
            val users = remember {
                mutableStateOf(listOf<Data>())
            }

            LaunchedEffect(Unit) {
                viewModel.users.collect {
                    if (it.data.isEmpty()){
                        simpleToast(context, it.meta.message)
                    } else {
                        users.value= it.data
                    }
                }
            }

            LaunchedEffect(Unit){
                viewModel.getAllUsers(loginData.id.toString())
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "${loginData.name} login as ${loginData.role}")
                Button(onClick = {
                    logout(navController)
                }) {
                    Text(text = "Logout")
                }
                when {
                    users.value.isNotEmpty() -> {
                        println(users)
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    content = {
                        items(users.value.size){
                            UserItem(users.value[it], viewModel)
                        }
                    }
                )
            }
        }
        "normal" -> {
            var page = 1
            val limit = 10
            val isLoad = remember {
                mutableStateOf(false)
            }
            val albums = remember {
                mutableStateOf(listOf<Photos>())
            }

            LaunchedEffect(Unit){
                viewModel.getAlbums(page, limit, context)
            }

            LaunchedEffect(Unit) {
                viewModel.albums.collect { photos ->
                    if (albums.value.isEmpty()){
                        albums.value = photos
                    } else {
                        val newList = ArrayList(albums.value)
                        newList.addAll(photos)
                        isLoad.value = false
                        delay(2000)
                        simpleToast(context,"Loaded")
                        albums.value = newList
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    item {
                        Button(
                            onClick = {
                                logout(navController)
                            }
                        ) {
                            Text(text = "Logout")
                        }
                    }
                    items(albums.value.size){
                        val photos = albums.value[it]
                        AlbumItem(photos = photos)
                        if (it == albums.value.size - 1 && !isLoad.value){
                            page++
                            LoadPhotos(page, viewModel, context)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun LoadPhotos(
    page: Int,
    viewModel: MainViewModel,
    context: Context
) {
    CircularProgressIndicator(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    )
    viewModel.getAlbums(page, 10, context)
}

@Composable
fun UserItem(data: Data, viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(10.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            UserItemTextTitle(text = data.name, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            UserItemText(text = "Id : ${data.id}", modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            UserItemText(text = "Username : ${data.username}", modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            UserItemText(text = "Email : ${data.email}", modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            UserItemText(text = "Role : ${data.role}", modifier = Modifier.padding(start = 10.dp, end = 10.dp))
        }

        Column(
            modifier = Modifier.align(Alignment.TopEnd),
            horizontalAlignment = Alignment.End
        ) {
            var expanded by remember { mutableStateOf(false) }

            DropdownMenu(
                modifier = Modifier
                    .background(Color.White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Edit",
                            fontSize = 12.sp,
                            fontFamily = manropeFamily,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Hapus",
                            fontSize = 12.sp,
                            fontFamily = manropeFamily,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        expanded = false
                    }
                )
            }
            Image(
                painter = painterResource(id = R.drawable.icon_action),
                contentDescription = "Action",
                modifier = Modifier
                    .size(height = 48.dp, width = 28.dp)
                    .clickable {
                        expanded = true
                    }
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AlbumItem(photos: Photos) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .padding(10.dp)
    ) {
        Card(
            modifier = Modifier
                .size(width = 74.dp, height = 74.dp)
                .padding(start = 10.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ){
            GlideImage(
                model = photos.thumbnailUrl,
                contentDescription = "Album Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.CenterStart
            )
        }

        AlbumItemTextTitle(
            text = photos.title,
            modifier = Modifier.padding(start = 94.dp, end = 10.dp)
        )
        AlbumIdItemText(
            text = "Id : ${photos.id}",
            modifier = Modifier.padding(start = 94.dp, end = 10.dp, top = 50.dp)
        )

        ClickableText(
            text = annotatedString(photos.url),
            onClick = {

            },
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 85.dp)
        )
    }
}

fun logout(navController: NavHostController) {
    navController.popBackStack()
    navController.navigate(Graph.AUTHENTICATION)
    destroy()
}

package com.example.yeoncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.platform.LocalConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodelabScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodelabScreen() { // 앱의 메인 레이아웃
    Scaffold(
        topBar = { SearchBar() },
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8F1EB))
        ) {
            if (isLandscape) {
                SideNavigationBar()
            }
            MainContent(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() { // 상단 검색창
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search") },
        leadingIcon = { // 돋보기 아이콘 추가
            Icon(painter = painterResource(id = android.R.drawable.ic_menu_search), contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
    )
}

@Composable // 카테고리 & 컬렉션
fun MainContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Align your Body", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        CategoryRow()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Favorite Collections", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        CollectionsGrid()
    }
}

@Composable
fun CategoryRow() { // 카테고리
    val categories = listOf("Inversions", "Quick yoga", "Stretching", "Tabata", "HIIT", "Pre-natal yoga")
    LazyRow {
        items(categories) { category ->
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(category)
            }
        }
    }
}

@Composable
fun CollectionsGrid() { // 즐겨찾기
    Column {
        Row {
            CollectionItem("Short mantras")
            Spacer(modifier = Modifier.width(8.dp))
            CollectionItem("Stress and anxiety")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            CollectionItem("Nature meditations")
            Spacer(modifier = Modifier.width(8.dp))
            CollectionItem("Self-care")
        }
    }
}

@Composable
fun CollectionItem(title: String) { // 카드형태(UI)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}

@Composable
fun BottomNavigationBar() { // 하단 네비게이션
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun SideNavigationBar() { // 가로모드
    Column(modifier = Modifier.padding(16.dp)) {
        Icon(Icons.Filled.Home, contentDescription = "Home")
        Spacer(modifier = Modifier.height(16.dp))
        Icon(Icons.Filled.Person, contentDescription = "Profile")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodelabScreen() {
    CodelabScreen()
}
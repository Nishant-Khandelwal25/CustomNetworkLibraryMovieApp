package com.example.customnetworkapimovie.screens.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.customnetworkapimovie.components.AppBar
import com.example.customnetworkapimovie.screens.latest_movies_screen.LatestMoviesScreen
import com.example.customnetworkapimovie.screens.popular_movies_screen.PopularMoviesScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val pagerState = rememberPagerState { 2 }

    Column {
        AppBar("Movies App")
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navController)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val tabs = listOf("Latest", "Popular")

    val scope = rememberCoroutineScope()

    TabRow(modifier = Modifier.height(64.dp), selectedTabIndex = pagerState.currentPage) {
        tabs.forEachIndexed { index, _ ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.scrollToPage(index) } }) {
                Text(
                    text = tabs[index]
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
        when (page) {
            0 -> LatestMoviesScreen(navController)

            1 -> PopularMoviesScreen(navController)
        }
    }
}
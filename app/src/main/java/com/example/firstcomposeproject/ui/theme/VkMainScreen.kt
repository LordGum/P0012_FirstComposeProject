package com.example.firstcomposeproject.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.MainViewModel
import com.example.firstcomposeproject.domain.StatisticType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    Scaffold (
        bottomBar = {
            BottomAppBar {
                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorites,
                    NavigationItem.Person
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = (selectedItemPosition.value == index),
                        onClick = { selectedItemPosition.value = index},
                        icon = {
                               Icon(item.icon, contentDescription = null)
                        },
                        label = { Text(text = stringResource(id = item.titleResId))},
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }
    ) {
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
           items(
               items = feedPosts.value,
               key = {it.id}
           ) {feedPost ->

               val dismissState = rememberDismissState()
               if(dismissState.isDismissed(DismissDirection.EndToStart)) viewModel.delete(feedPost)

               SwipeToDismiss(
                   modifier = Modifier.animateItemPlacement(),
                   state = dismissState,
                   directions = setOf(DismissDirection.EndToStart),
                   background = {},
                   dismissContent = {
                       PostCard(
                           feedPost = feedPost,
                           onViewClickListener = {
                               viewModel.updateCount(feedPost, StatisticType.VIEWS)
                           },
                           onShareClickListener = {
                               viewModel.updateCount(feedPost, StatisticType.SHARES)
                           },
                           onCommentClickListener = {
                               viewModel.updateCount(feedPost, StatisticType.COMMENTS)
                           },
                           onLikeClickListener = {
                               viewModel.updateCount(feedPost, StatisticType.LIKES)
                           }
                       )
                   }
               )
           }
        }
    }
}
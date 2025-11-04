package com.example.bottomnavigationdemo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.bottomnavigationdemo.model.navItems
import com.example.bottomnavigationdemo.pages.HomePage
import com.example.bottomnavigationdemo.pages.NotificationPage
import com.example.bottomnavigationdemo.pages.SettingsPage

@Composable
fun MainScreen() {
    val selectedIndex = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex.value,
                        onClick = { selectedIndex.value = index },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount > 0) {
                                        Badge {
                                            Text(item.badgeCount.toString())
                                        }
                                    }
                                }
                            ) {
                                Icon(item.icon, contentDescription = item.label)
                            }
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedIndex.value) {
            0 -> HomePage(modifier = Modifier.padding(paddingValues))
            1 -> NotificationPage(modifier = Modifier.padding(paddingValues))
            2 -> SettingsPage(modifier = Modifier.padding(paddingValues))
        }
    }
}

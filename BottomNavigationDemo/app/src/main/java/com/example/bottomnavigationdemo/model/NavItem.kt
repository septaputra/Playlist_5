package com.example.bottomnavigationdemo.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val badgeCount: Int
)

val navItems = listOf(
    NavItem("Home", Icons.Default.Home, 0),
    NavItem("Notifications", Icons.Default.Notifications, 5),
    NavItem("Settings", Icons.Default.Settings, 0)
)

package com.example.listdemoapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.*
import android.widget.Toast
import com.example.listdemoapp.data.getAllMarvelChars
import com.example.listdemoapp.ui.components.MarvelItem

@Composable
fun LazyColumnDemo() {
    val context = LocalContext.current
    val listMarvelChars = getAllMarvelChars()

    LazyColumn {
        items(
            items = listMarvelChars,
            key = { char -> char.charName }
        ) { character ->
            MarvelItem(character) {
                Toast.makeText(context, "${character.charName} Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

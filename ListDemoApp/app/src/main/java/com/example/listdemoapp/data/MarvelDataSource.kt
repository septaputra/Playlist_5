package com.example.listdemoapp.data

import com.example.listdemoapp.R
import com.example.listdemoapp.model.MarvelCharacter

fun getAllMarvelChars(): List<MarvelCharacter> {
    return listOf(
        MarvelCharacter("Iron Man", "Robert Downey Jr.", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Captain America", "Chris Evans", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Thor", "Chris Hemsworth", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Black Widow", "Scarlett Johansson", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Hulk", "Mark Ruffalo", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Hawkeye", "Jeremy Renner", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Captain Marvel", "Brie Larson", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Spider-Man", "Tom Holland", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Doctor Strange", "Benedict Cumberbatch", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Black Panther", "Chadwick Boseman", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Ant-Man", "Paul Rudd", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Wasp", "Evangeline Lilly", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Falcon", "Anthony Mackie", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Winter Soldier", "Sebastian Stan", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Vision", "Paul Bettany", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Scarlet Witch", "Elizabeth Olsen", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Loki", "Tom Hiddleston", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Thanos", "Josh Brolin", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Deadpool", "Ryan Reynolds", R.drawable.ic_launcher_foreground),
        MarvelCharacter("Wolverine", "Hugh Jackman", R.drawable.ic_launcher_foreground)
    )
}

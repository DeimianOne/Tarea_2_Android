package com.example.mediaexplorer

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mediaexplorer.ui.views.AnimeScreen
import com.example.mediaexplorer.ui.views.HomeScreen
import com.example.mediaexplorer.ui.views.FormCreateScreen
import com.example.mediaexplorer.ui.views.MovieScreen
import com.example.mediaexplorer.ui.views.SecondScreen
import com.example.mediaexplorer.ui.views.SeriesScreen
import kotlinx.serialization.Serializable
import java.util.Locale.Category

@Serializable
object Home

@Serializable
object MovieSc

@Serializable
object SerieSc

@Serializable
object AnimeSc

@Serializable
object FormCreate

@Serializable
data class SecondPage(val id: Int)

/*CREACION DE CLASES*/
open class Content(val id:Int, val name:String, val information:String,val category:TypeContent, val imageResId: Int )
class Movies(id:Int, name:String, information:String, category:TypeContent, imageResId: Int, val duration:Int): Content(id, name, information, category, imageResId)
class Series(id:Int, name:String, information:String,category:TypeContent, imageResId: Int , val cantCap:Int): Content(id, name, information, category, imageResId)
class Anime(id:Int, name:String, information:String,category:TypeContent, imageResId: Int , val cantCap:Int, typeGender:String): Content(id, name, information, category, imageResId)
class OtherContent(id:Int, name:String, information:String,category:TypeContent, imageResId: Int , val typeContent:String): Content(id, name, information, category, imageResId)

/*CONTENIDO PREDETERMINADO*/
class CardCategory(val name: String, val image: Int)

//var ListMovies:MutableList<Movies> = mutableListOf()
var ListSeries:MutableList<Series> = mutableListOf()
var ListAnimes:MutableList<Anime> = mutableListOf()
var ListOtherContent:MutableList<OtherContent> = mutableListOf()

var pelicula = CardCategory(TypeContent.PELICULA.displayName, R.drawable.pelicula)
var series = CardCategory(TypeContent.SERIE.displayName, R.drawable.series)
var anime = CardCategory(TypeContent.ANIME.displayName, R.drawable.anime)

@Composable
fun Navigation(){
    var ListMovies = remember { mutableStateListOf<Movies>() }
    var Category = remember{mutableStateListOf<CardCategory>(pelicula, series, anime)}
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home){
        composable<Home>{
            HomeScreen(navController = navController, category = Category)
        }
        composable<MovieSc>{
            MovieScreen(navController = navController, movies = ListMovies)
        }
        composable<SerieSc>{
            SeriesScreen(navController = navController)
        }
        composable<AnimeSc>{
            AnimeScreen(navController = navController)
        }

        composable<FormCreate>{
            FormCreateScreen(navController = navController)
        }
        composable<SecondPage>{ backStackEntry ->
            val args = backStackEntry.toRoute<SecondPage>()
            SecondScreen(navController, args.id, ListMovies)
        }
    }
}

enum class TypeContent(val displayName: String) {
    PELICULA("Película"),
    SERIE("Serie"),
    ANIME("Anime"),
    OTRO("Otro")
}
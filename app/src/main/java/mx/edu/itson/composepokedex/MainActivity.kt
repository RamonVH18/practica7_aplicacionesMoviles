package mx.edu.itson.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.itson.composepokedex.domain.Pokemon
import mx.edu.itson.composepokedex.ui.theme.ComposePokedexTheme

class MainActivity : ComponentActivity() {
    val pokemon = Pokemon("Pikachu", 25,
        "Electrico",
        "Rata amarilla Electrica",
        0.4f, 6f,
        true, "Estatica", R.drawable.pikachu)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        pokemon,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonHeader(name:String, number:Int, fav:Boolean) {
    Row(Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column() {
            Text(name, )
            Text("#${number}", modifier = Modifier.align(Alignment.End))
        }
        Box{
            Image(painter = painterResource(R.drawable.pokeball),
                contentDescription = "pokebal image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(130.dp).offset(30.dp, 20.dp)
            )
            Image(painter = painterResource(if(fav)R.drawable.star_filled else R.drawable.star_outline),
                contentDescription = if (fav) "yellow star filled" else "yellow star outlined",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun PokemonCard(name: String, weight: Float, height: Float, description: String, ability: String, image: Int) {
    Card{
        Image(painter = painterResource(image), contentDescription = name)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHeaderPreview(){
    ComposePokedexTheme() {
        PokemonHeader("Pikachu", 25, true)
    }
}
@Composable
fun Greeting(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column{
        PokemonHeader(pokemon.name, pokemon.number, pokemon.fav)
        PokemonCard(pokemon.name, pokemon.weight, pokemon.height, pokemon.description, pokemon.ability, pokemon.image)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePokedexTheme {
        Greeting(Pokemon("Pikachu", 25,
            "Electrico",
            "Rata amarilla electrica",
            0.4f, 6f,
            true, "Estatica", R.drawable.pikachu))
    }
}
package br.com.alura.aluvery.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.screens.stateholdes.HomeScreenUiStateHolder
import br.com.alura.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {

    private val productDao: ProductDao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(fabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val products = productDao.products()
                HomeScreen(products = products)
            }
        }
    }
}

@Composable
fun App(
    fabClick: () -> Unit = {}, content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = fabClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {

    App {
        HomeScreen(
            HomeScreenUiStateHolder(
                sections = sampleSections
            )
        )
    }
}
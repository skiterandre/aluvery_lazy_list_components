package br.com.alura.aluvery.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.ui.screens.ProductFormScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {

    private val productDao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(onClickSalvar = {product ->
                        productDao.save(product)
                        finish()
                    })
                }
            }
        }
    }
}

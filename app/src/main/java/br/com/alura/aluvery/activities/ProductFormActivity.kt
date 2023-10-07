package br.com.alura.aluvery.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.NumberFormatException
import java.math.BigDecimal
import javax.security.auth.login.LoginException

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Cadastro de Produto",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )

        var url by remember {
            mutableStateOf("")
        }
        if(url.isNotEmpty()){
            AsyncImage(model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp))
        }
        TextField(
            value = url,
            onValueChange = {
                url = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("url")
            }
        )

        var nome by remember {
            mutableStateOf("")
        }
        TextField(value = nome,
            onValueChange = {
                nome = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Nome")
            })

        var preco by remember {
            mutableStateOf("")
        }
        TextField(value = preco,
            onValueChange = {
                preco = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Preço")
            })

        var descricao by remember {
            mutableStateOf("")
        }
        TextField(value = descricao,
            onValueChange = {
                descricao = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text("Descrição")
            })

        Button(
            onClick = {
                val convertedPrice = try{
                    BigDecimal(preco)
                }catch (e: NumberFormatException){
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = nome,
                    image = url,
                    price = convertedPrice,
                    description = descricao
                )
                Log.i("ProductFormActivity", "ProductForm: $product")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}
package br.com.alura.aluvery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.screens.stateholdes.ProductFormScreenUiStateHolder
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun ProductFormScreen(onClickSalvar: (Product)-> Unit ){
    var url by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var preco by remember {
        mutableStateOf("")
    }
    var formatter = remember {
        DecimalFormat("#.##")
    }
    var description by remember {
        mutableStateOf("")
    }
    val productFormScreenUiStateHolder = remember(url, preco, description, name) {
        ProductFormScreenUiStateHolder(
            name = name,
            onNameChanged = {
                name = it
            },
            url = url,
            onUrlChanged = {
                url = it
            },
            price = preco,
            onPriceChanged = {
                try {
                    preco = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isEmpty()) {
                        preco = it
                    }
                }
            },
            formater = formatter,
            description = description,
            onDecriptionChanged = {
                description = it
            },
            onClickSalvar = onClickSalvar)
    }
    ProductFormScreen(productFormScreenUiStateHolder)
}
@Composable
fun ProductFormScreen(
    state: ProductFormScreenUiStateHolder = ProductFormScreenUiStateHolder()
) {
    val onClickSalvar = state.onClickSalvar
    val name = state.name
    val onNameChanged = state.onNameChanged
    val url = state.url
    val onUrlChanged = state.onUrlChanged
    val isShowPreview = state.isShowPreview
    val preco = state.price
    val onPrecoChanged = state.onPriceChanged
    val descricao = state.description
    val onDescriptionChanged = state.onDecriptionChanged

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


        if (isShowPreview) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        TextField(
            value = url,
            onValueChange = onUrlChanged,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("url")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = name,
            onValueChange = onNameChanged,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )


        TextField(
            value = preco,
            onValueChange = onPrecoChanged,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )


        TextField(
            value = descricao,
            onValueChange = onDescriptionChanged,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text("Descrição")
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(
            onClick = {
                val convertedPrice = try {
                    BigDecimal(preco)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    image = url,
                    price = convertedPrice,
                    description = descricao
                )
                onClickSalvar.invoke(product)
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
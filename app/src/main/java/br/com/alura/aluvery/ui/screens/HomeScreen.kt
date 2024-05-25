package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.screens.stateholdes.HomeScreenUiStateHolder
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(products: List<Product>){
    val section = mapOf(
        "Todos os Produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by remember {
        mutableStateOf("")
    }
    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) ?: false
    }
    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts
                .filter(containsInNameOrDescription()) +
                    products.filter(containsInNameOrDescription())
        } else {
            emptyList()
        }
    }
    val homeScreenUiStateHolder = remember(products, text) {
        HomeScreenUiStateHolder(
            sections = section,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchText = {
                text = it
            }
        )
    }
    HomeScreen(
        state = homeScreenUiStateHolder
    )
}

@Composable
fun HomeScreen(
    state: HomeScreenUiStateHolder = HomeScreenUiStateHolder(emptyMap())
) {

    val text = state.searchText
    val searchedProducts = state.searchedProducts
    val sections = state.sections
    Column {
        SearchTextField(searchText = text,
            onSearchTextChange = state.onSearchText
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }

                }
            } else {
                items(searchedProducts) { productItem ->
                    CardProductItem(
                        product = productItem,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiStateHolder(
                sections = sampleSections)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewWithSeachText() {
    AluveryTheme {
        Surface {
            HomeScreen(
                HomeScreenUiStateHolder(searchText = "Hamburguer",
                    sections = sampleSections)
            )
        }
    }
}
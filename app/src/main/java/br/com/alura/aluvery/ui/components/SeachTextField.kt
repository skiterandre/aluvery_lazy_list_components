package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchTextField(searchText:String, onSearchTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Icone de busca"
            )
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text(text = "O que você procura?")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    var text by remember {
        mutableStateOf("")
    }
    SearchTextField(text) {
        text = it
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreviewWithSearchTExt() {
    var text by remember {
        mutableStateOf("Hamburguer")
    }
    SearchTextField(text){
        text = it
    }
}
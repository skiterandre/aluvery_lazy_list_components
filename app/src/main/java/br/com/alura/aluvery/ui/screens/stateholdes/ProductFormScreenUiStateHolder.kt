package br.com.alura.aluvery.ui.screens.stateholdes

import br.com.alura.aluvery.model.Product
import java.text.DecimalFormat

class ProductFormScreenUiStateHolder(
    val name: String = "",
    val onNameChanged: (String) -> Unit = {},
    val onClickSalvar: (Product) -> Unit = {},
    val url: String = "",
    val onUrlChanged: (String) -> Unit = {},
    val isShowPreview: Boolean = url.isNotBlank(),
    val price: String = "",
    val onPriceChanged: (String) -> Unit = {},
    val description: String = "",
    val onDecriptionChanged: (String) -> Unit = {},
    val formater: DecimalFormat = DecimalFormat("#.##")
) {

}
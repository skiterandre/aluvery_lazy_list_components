package br.com.alura.aluvery.ui.screens.stateholdes

import br.com.alura.aluvery.model.Product

class HomeScreenUiStateHolder(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts:List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchText:(String)-> Unit = {}
){
    fun isShowSections() =  searchText.isNullOrBlank()
}
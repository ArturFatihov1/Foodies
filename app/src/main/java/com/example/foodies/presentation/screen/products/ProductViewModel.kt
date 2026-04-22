package com.example.foodies.presentation.screen.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.domain.GetAllProductsUseCase
import com.example.foodies.domain.GetCategoriesProductUseCase
import com.example.foodies.domain.GetDetailProductUseCase
import com.example.foodies.presentation.screen.detail.DetailState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getDetailProductUseCase: GetDetailProductUseCase,
    private val getCategoriesProductUseCase: GetCategoriesProductUseCase,
) : ViewModel() {

    private val _productState = MutableStateFlow<ProductState>(ProductState.Loading)
    val productState = _productState.asStateFlow()

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState = _detailState.asStateFlow()

    private val _categoriesState = MutableStateFlow<List<String>>(emptyList())
    val categoriesState = _categoriesState.asStateFlow()

    private val _selectedCategories = MutableStateFlow<String>("")
    val selectedCategories = _selectedCategories.asStateFlow()

    init {
        load()
        loadNameCategory()
    }

    fun load() {
        _productState.value = ProductState.Loading
        viewModelScope.launch {
            try {
                delay(2000)
                val data = getAllProductsUseCase.getAllProducts()
                _productState.value = ProductState.Success(products = data)
            } catch (e: Exception) {
                _productState.value = ProductState.Error(message = e.toString())
            }
        }
    }

    fun loadDetail(id: Long) {
        _detailState.value = DetailState.Loading
        viewModelScope.launch {
            try {
                val product = getDetailProductUseCase.getProductById(id)
                delay(1500)
                _detailState.value = DetailState.Success(product)
            } catch (e: Exception) {
                _detailState.value = DetailState.Error(message = e.toString())
            }
        }
    }

    fun loadNameCategory() {
        viewModelScope.launch {
            try {
                val data = getCategoriesProductUseCase.getNameCategories()
                _categoriesState.value = data
            } catch (e: Exception) {
                Log.d("Login123", "Category not FOUND")
            }
        }
    }

    fun loadCategoryProducts(category: String) {
        _productState.value = ProductState.Loading
        viewModelScope.launch {
            try {
                val data = getCategoriesProductUseCase.getProductCategories(category)
                _productState.value = ProductState.Success(products = data)
            } catch (e: Exception) {
                _productState.value = ProductState.Error(message = e.toString())
            }
        }
    }

    fun selectCategory(category: String) {
        _selectedCategories.value = category
        loadCategoryProducts(category)
    }
}
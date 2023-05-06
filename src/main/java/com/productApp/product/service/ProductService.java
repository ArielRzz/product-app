package com.productApp.product.service;

import com.productApp.product.dto.ProductDto;
import com.productApp.product.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    ProductDto saveProduct(ProductDto product);


    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);
}

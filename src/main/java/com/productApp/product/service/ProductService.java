package com.productApp.product.service;

import com.productApp.product.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    ProductDto getProductByName(String name);

    ProductDto saveProduct(ProductDto product);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

/*    List<ProductDto> getProductsOrderByPrice();*/

    ProductDto getProductById(Long productId);
}

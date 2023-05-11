package com.productApp.product.controller;
import com.productApp.product.dto.ProductDto;
import com.productApp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        var products = productService.getProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/products/sorted")
    public ResponseEntity<List<ProductDto>> getAllSortedProducts() {
        var products = productService.getProductsOrderByPrice();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/products/name/{productName}")
    public ResponseEntity<List<ProductDto>> getProductByName(@PathVariable String productName) {
        List<ProductDto> productListByName=  productService.getListProductByName(productName);
        if (productListByName.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productListByName, HttpStatus.OK);
        }
    }

    @PostMapping("/products/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/update")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,
                                                      @RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.updateProduct(productId,product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
          productService.deleteProduct(productId);
          return new ResponseEntity<>("Product successfully deleted", HttpStatus.ACCEPTED);
    }
}

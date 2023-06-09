package com.productApp.product.controller;

import com.productApp.product.dto.ProductDto;
import com.productApp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

/*    @GetMapping("/products/sorted")
    public ResponseEntity<List<ProductDto>> getAllSortedProducts() {
        var products = productService.getProductsOrderByPrice();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }*/

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/products/name/{productName}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
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
        Optional<ProductDto> product = Optional.ofNullable(productService.getProductById(productId));
        if (product.isPresent()) {
            productService.deleteProduct(productId);
            return new ResponseEntity<>("Product successfully deleted", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}

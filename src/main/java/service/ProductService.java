package service;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getProductsOrderByPrice();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Product product);

}

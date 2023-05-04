package service.Impl;

import model.Product;
import org.springframework.stereotype.Service;
import service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    private IProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsOrderByPrice() {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Product product) {

    }
}

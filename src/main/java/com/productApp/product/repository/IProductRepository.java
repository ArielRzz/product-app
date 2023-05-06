package com.productApp.product.repository;

import com.productApp.product.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository  extends JpaRepository<Product,Long> {
/*    List<Product> getAllAndOrderByPrice(Sort sort);*/
    Product getProductByName(String name);
}

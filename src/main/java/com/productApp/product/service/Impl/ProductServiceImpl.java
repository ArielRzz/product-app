package com.productApp.product.service.Impl;

import com.productApp.product.dto.ProductDto;
import jakarta.persistence.EntityNotFoundException;
import com.productApp.product.mapper.ProductMapper;
import com.productApp.product.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.productApp.product.repository.IProductRepository;
import com.productApp.product.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductServiceImpl(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.convertToDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsOrderByPrice() {
        Sort sort = Sort.by("price").ascending();
        List<Product> sorted =  productRepository.getAllAndOrderByPrice(sort);
        return productMapper.convertToDtoList(sorted);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.getReferenceById(productId);
        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto getProductByName(String name) {
        Product product = productRepository.getProductByName(name);
        return productMapper.convertToDto(product);
    }
    @Override
    public ProductDto saveProduct(ProductDto product) {
        Product productSaved = productRepository.save(productMapper.convertToEntity(product));
        return productMapper.convertToDto(productSaved);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("The product with the provided ID (%s) was not found", productId)));
        mergeData(productDto, product);
        return productMapper.convertToDto(productRepository.save(product));
    }

    private static void mergeData(ProductDto productDto, Product product) {
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if (productDto.getQuantity() != null) {
            product.setQuantity(productDto.getQuantity());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "The Product with ID %s was not found. It is not possible to delete a product that does not exist. Please check if the product ID is correct and try again.",
                        productId)));
        productRepository.delete(product);
    }


}

package com.productApp.product.mapper;

import com.productApp.product.dto.ProductDto;
import com.productApp.product.model.Product;
import com.productApp.product.utils.MapperUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper  {

    public ProductDto convertToDto(Product product) {
        return MapperUtil.map(product, ProductDto.class);
    }

    public Product convertToEntity(ProductDto productDto) {
        return MapperUtil.map(productDto, Product.class);
    }

    public List<ProductDto> convertToDtoList(List<Product> productList) {
        return MapperUtil.mapList(productList, ProductDto.class);
    }

    public List<Product> convertToEntityList(List<ProductDto> productDtoList) {
        return MapperUtil.mapList(productDtoList, Product.class);
    }
}

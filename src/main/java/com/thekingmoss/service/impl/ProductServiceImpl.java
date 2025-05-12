package com.thekingmoss.service.impl;

import com.thekingmoss.dto.category.CategoryResponseDto;
import com.thekingmoss.dto.product.ProductRequestDto;
import com.thekingmoss.dto.product.ProductResponseDto;
import com.thekingmoss.entity.Category;
import com.thekingmoss.entity.Product;
import com.thekingmoss.exception.ResourceNotFoundException;
import com.thekingmoss.mapper.category.CategoryMapper;
import com.thekingmoss.mapper.product.ProductMapper;
import com.thekingmoss.repository.IProductRepository;
import com.thekingmoss.service.ICategoryService;
import com.thekingmoss.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDo)
                .toList();
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDo)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public List<ProductResponseDto> listProductsByCategoryName(String categoryName) {
        return productRepository
                .findByCategory_Name(categoryName)
                .stream()
                .map(productMapper::toDo)
                .toList();
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(requestDto.getCategoryId());
        Category category = categoryMapper.toEntityCategory(categoryResponseDto);
        Product product = productMapper.toEntity(requestDto, category);
        return productMapper.toDo(productRepository.save(product));
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        // Search for existing product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Get the category by ID from the DTO
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(requestDto.getCategoryId());
        Category category = categoryMapper.toEntityCategory(categoryResponseDto);

        // Update the fields
        product.setName(requestDto.getName());
        product.setStock(requestDto.getStock());
        product.setUnitPrice(requestDto.getUnitPrice());
        product.setDiscount(requestDto.getDiscount());
        product.setDescription(requestDto.getDescription());
        product.setSize(requestDto.getSize());
        product.setWeight(requestDto.getWeight());
        product.setCategory(category);

        // Save and return DTO
        return productMapper.toDo(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID : " + id);
        }
        productRepository.deleteById(id);
    }
}

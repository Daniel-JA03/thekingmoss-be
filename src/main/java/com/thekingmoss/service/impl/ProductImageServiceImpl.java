package com.thekingmoss.service.impl;

import com.thekingmoss.dto.product.ProductResponseDto;
import com.thekingmoss.dto.productImage.ProductImageRequestDto;
import com.thekingmoss.dto.productImage.ProductImageResponseDto;
import com.thekingmoss.entity.Product;
import com.thekingmoss.entity.ProductImage;
import com.thekingmoss.exception.ResourceNotFoundException;
import com.thekingmoss.mapper.product.ProductMapper;
import com.thekingmoss.mapper.productImage.ProductImageMapper;
import com.thekingmoss.repository.IProductImageRepository;
import com.thekingmoss.service.IProductService;
import com.thekingmoss.service.IProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements IProductImageService {

    private final IProductImageRepository productImageRepository;
    private final IProductService productService;
    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;


    @Override
    public List<ProductImageResponseDto> getAllProductImage() {
        return productImageRepository.findAll().stream()
                .map(productImageMapper::toDto)
                .toList();
    }

    @Override
    public ProductImageResponseDto createProductImage(ProductImageRequestDto requestDto) {
        ProductResponseDto productResponseDto = productService.getProductById(requestDto.getProductId());
        Product product = productMapper.toEntity(productResponseDto);
        ProductImage productImage = productImageMapper.toEntity(requestDto,product);
        return productImageMapper.toDto(productImageRepository.save(productImage));

    }

    @Override
    public ProductImageResponseDto getProductImageById(Long id) {
        return productImageRepository.findById(id)
                .map(productImageMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("ProductImage not found with ID:" + id));
    }


    @Override
    public void deleteById(Long id) {
        if (!productImageRepository.existsById(id)){
            throw new ResourceNotFoundException("ProductImage not found with ID: " + id);
        }
        productImageRepository.deleteById(id);

    }
}

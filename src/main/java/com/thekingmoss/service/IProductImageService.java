package com.thekingmoss.service;

import com.thekingmoss.dto.productImage.ProductImageRequestDto;
import com.thekingmoss.dto.productImage.ProductImageResponseDto;

import java.util.List;

public interface IProductImageService {

    List<ProductImageResponseDto> getAllProductImage();

    ProductImageResponseDto createProductImage(ProductImageRequestDto requestDto);

    ProductImageResponseDto getProductImageById(Long id);

    void deleteById(Long id);
}

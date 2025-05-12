package com.thekingmoss.controller;

import com.thekingmoss.dto.productImage.ProductImageRequestDto;
import com.thekingmoss.dto.productImage.ProductImageResponseDto;
import com.thekingmoss.service.IProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productImage")
@RequiredArgsConstructor
public class ProductImageController {

    private final IProductImageService service;

    @GetMapping
    public List<ProductImageResponseDto> getAll() {
        return service.getAllProductImage();
    }

    @PostMapping
    public ProductImageResponseDto create(@RequestBody ProductImageRequestDto requestDto) {
        return service.createProductImage(requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ProductImageResponseDto getById(@PathVariable Long id) {
        return service.getProductImageById(id);
    }
}



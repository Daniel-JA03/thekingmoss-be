package com.thekingmoss.controller;

import com.thekingmoss.dto.category.CategoryRequestDto;
import com.thekingmoss.dto.category.CategoryResponseDto;
import com.thekingmoss.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService service;

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return service.getAllCategories();
    }

    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryRequestDto requestDto) {
        return service.createCategory(requestDto);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto requestDto) {
        return service.updateCategory(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}

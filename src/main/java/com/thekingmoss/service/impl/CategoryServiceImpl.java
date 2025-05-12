package com.thekingmoss.service.impl;

import com.thekingmoss.dto.category.CategoryRequestDto;
import com.thekingmoss.dto.category.CategoryResponseDto;
import com.thekingmoss.entity.Category;
import com.thekingmoss.exception.ResourceNotFoundException;
import com.thekingmoss.mapper.category.CategoryMapper;
import com.thekingmoss.repository.ICategoryRepository;
import com.thekingmoss.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDo)
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDo)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        Category category = categoryMapper.toEntity(requestDto);
        return categoryMapper.toDo(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        category.setName(requestDto.getName());

        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDo(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID :" + id);
        }
        categoryRepository.deleteById(id);
    }
}

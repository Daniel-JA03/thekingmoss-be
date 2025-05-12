package com.thekingmoss.repository;

import com.thekingmoss.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageRepository extends JpaRepository<ProductImage,Long> {

}

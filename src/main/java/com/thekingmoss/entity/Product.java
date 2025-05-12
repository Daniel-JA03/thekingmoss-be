package com.thekingmoss.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long productId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "stock", nullable = false)
  private Integer stock;

  @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal unitPrice;

  @Column(name = "discount", nullable = false, precision = 5, scale = 2)
  private BigDecimal discount;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "size")
  private String size;
  
  @Column(name = "weight", precision = 10, scale = 2)
  private BigDecimal weight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  @Builder.Default
  private List<ProductImage> productImages = new ArrayList<>();
  
  @OneToMany(mappedBy = "product")
  @Builder.Default
  private Set<OrderDetail> orderDetails = new LinkedHashSet<>();
}

package com.thekingmoss.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderDetail {
  
  @EmbeddedId
  private OrderDetailId orderDetailId;

  @MapsId("orderId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @MapsId("productId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private Integer quantity;

  public OrderDetail(Order order, Product product, Integer quantity) {
    this.order = order;
    this.product = product;
    this.quantity = quantity;
    this.orderDetailId = new OrderDetailId(order.getOrderId(), product.getProductId());
  }

}

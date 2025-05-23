package com.thekingmoss.mapper.orderDetail;

import com.thekingmoss.entity.OrderDetail;
import com.thekingmoss.entity.Order;
import com.thekingmoss.entity.OrderDetailId;
import com.thekingmoss.entity.Product;
import com.thekingmoss.dto.orderDetail.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderDetailMapper {

    public OrderDetail toEntity(OrderDetailRequestDto dto, Order order, Product product) {
        // explicitly create an OrderDetailId
        OrderDetailId id = new OrderDetailId(order.getOrderId(), product.getProductId());

        return OrderDetail.builder()
                .orderDetailId(id)
                .order(order)
                .product(product)
                .quantity(dto.getQuantity())
                .build();
    }

    public OrderDetailResponseDto toDto(OrderDetail detail) {
        return OrderDetailResponseDto.builder()
                .productId(detail.getProduct().getProductId())
                .productName(detail.getProduct().getName())
                .quantity(detail.getQuantity())
                .unitPrice(detail.getProduct().getUnitPrice())
                .discount(detail.getProduct().getDiscount()) // The discount field was missing
                .build();
    }
}


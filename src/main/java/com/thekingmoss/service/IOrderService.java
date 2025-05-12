package com.thekingmoss.service;

import java.util.List;

import com.thekingmoss.dto.order.OrderRequestDto;
import com.thekingmoss.dto.order.OrderResponseDto;
import com.thekingmoss.dto.orderDetail.OrderDetailRequestDto;
import com.thekingmoss.dto.orderDetail.OrderDetailResponseDto;

public interface IOrderService {
    OrderResponseDto createOrder(OrderRequestDto dto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto updateOrder(Long id, OrderRequestDto dto);
    void deleteOrder(Long id);
    OrderDetailResponseDto addOrderDetail(Long orderId, OrderDetailRequestDto dto);
    void removeOrderDetail(Long orderId, Long productId);
}

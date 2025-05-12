package com.thekingmoss.service;

import com.thekingmoss.dto.orderDetail.OrderDetailRequestDto;
import com.thekingmoss.dto.orderDetail.OrderDetailResponseDto;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailResponseDto> getAllDetailsByOrderId(Long orderId);
    OrderDetailResponseDto updateOrderDetail(Long orderId, Long productId, OrderDetailRequestDto dto);
}

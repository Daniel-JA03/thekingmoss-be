package com.thekingmoss.service.impl;

import com.thekingmoss.dto.orderDetail.OrderDetailRequestDto;
import com.thekingmoss.dto.orderDetail.OrderDetailResponseDto;
import com.thekingmoss.entity.OrderDetail;
import com.thekingmoss.entity.OrderDetailId;
import com.thekingmoss.mapper.orderDetail.OrderDetailMapper;
import com.thekingmoss.repository.IOrderDetailRepository;
import com.thekingmoss.repository.IOrderRepository;
import com.thekingmoss.repository.IProductRepository;
import com.thekingmoss.service.IOrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailResponseDto> getAllDetailsByOrderId(Long orderId) {
        orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));

        return orderDetailRepository.findAllByOrder_OrderId(orderId).stream()
                .map(orderDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDetailResponseDto updateOrderDetail(Long orderId, Long productId, OrderDetailRequestDto dto) {
        orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));

        OrderDetailId id = new OrderDetailId(orderId, productId);
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderDetail not found: " + id));

        existing.setQuantity(dto.getQuantity());
        return orderDetailMapper.toDto(orderDetailRepository.save(existing));
    }



}

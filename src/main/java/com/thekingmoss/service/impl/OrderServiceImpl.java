package com.thekingmoss.service.impl;

import com.thekingmoss.dto.order.OrderRequestDto;
import com.thekingmoss.dto.order.OrderResponseDto;
import com.thekingmoss.dto.orderDetail.OrderDetailRequestDto;
import com.thekingmoss.dto.orderDetail.OrderDetailResponseDto;
import com.thekingmoss.entity.*;
import com.thekingmoss.exception.ResourceNotFoundException;
import com.thekingmoss.mapper.order.OrderMapper;
import com.thekingmoss.mapper.orderDetail.OrderDetailMapper;
import com.thekingmoss.repository.IOrderDetailRepository;
import com.thekingmoss.repository.IOrderRepository;
import com.thekingmoss.repository.IProductRepository;
import com.thekingmoss.repository.IUserRepository;
import com.thekingmoss.service.IOrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Order order = orderMapper.toEntity(dto, user);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found by ID : " + orderId));
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        order.setOrderDate(dto.getOrderDate());
        order.setDeliveryType(dto.getDeliveryType());
        order.setOrderInfo(dto.getOrderInfo());
        order.setDeliveryInstructions(dto.getDeliveryInstructions());
        order.setOrderStatus(dto.getOrderStatus());
        order.setUser(user);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) throw new RuntimeException("Order not found by ID : " + orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public OrderDetailResponseDto addOrderDetail(Long orderId, OrderDetailRequestDto orderDetailRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found by ID : " + orderId));
        Product product = productRepository.findById(orderDetailRequestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found by ID : " + orderDetailRequestDto.getProductId()));
        OrderDetail detail = orderDetailMapper.toEntity(orderDetailRequestDto, order, product);
        return orderDetailMapper.toDto(orderDetailRepository.save(detail));
    }

    @Override
    @Transactional
    public void removeOrderDetail(Long orderId, Long productId) {
        OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
        if (!orderDetailRepository.existsById(orderDetailId))
            throw new RuntimeException("OrderDetail not found by ID : " + orderDetailId);
        orderDetailRepository.deleteById(orderDetailId);
    }
}

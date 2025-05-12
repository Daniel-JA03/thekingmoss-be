package com.thekingmoss.controller;


import com.thekingmoss.dto.orderDetail.OrderDetailRequestDto;
import com.thekingmoss.dto.orderDetail.OrderDetailResponseDto;
import com.thekingmoss.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailResponseDto>> getAllDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailService.getAllDetailsByOrderId(orderId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<OrderDetailResponseDto> updateOrderDetail(
            @PathVariable Long orderId,
            @PathVariable Long productId,
            @RequestBody OrderDetailRequestDto dto) {
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(orderId, productId, dto));
    }
}

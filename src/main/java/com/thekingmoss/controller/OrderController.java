package com.thekingmoss.controller;


import com.thekingmoss.dto.order.OrderRequestDto;
import com.thekingmoss.dto.order.OrderResponseDto;
import com.thekingmoss.dto.orderDetail.OrderDetailRequestDto;
import com.thekingmoss.dto.orderDetail.OrderDetailResponseDto;
import com.thekingmoss.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder( @RequestBody OrderRequestDto dto) {
        return new ResponseEntity<>(orderService.createOrder(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/details")
    public ResponseEntity<OrderDetailResponseDto> addOrderDetail(@PathVariable Long orderId, @RequestBody OrderDetailRequestDto dto) {
        return new ResponseEntity<>(orderService.addOrderDetail(orderId, dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}/details/{productId}")
    public ResponseEntity<Void> removeOrderDetail(@PathVariable Long orderId, @PathVariable Long productId) {
        orderService.removeOrderDetail(orderId, productId);
        return ResponseEntity.noContent().build();
    }

}
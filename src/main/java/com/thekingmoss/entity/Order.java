package com.thekingmoss.entity;

import com.thekingmoss.entity.types.OrderStatusType;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "delivery_type", nullable = false)
    private String deliveryType;

    @Column(name = "order_info", nullable = false)
    private String orderInfo;

    @Column(name = "delivery_instructions", nullable = false)
    private String deliveryInstructions;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatusType orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "order")
    @Builder.Default
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();
}

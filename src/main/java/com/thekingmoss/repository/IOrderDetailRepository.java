package com.thekingmoss.repository;

import com.thekingmoss.entity.OrderDetail;
import com.thekingmoss.entity.OrderDetailId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IOrderDetailRepository extends JpaRepository <OrderDetail, OrderDetailId>{
    List<OrderDetail> findAllByOrder_OrderId(Long orderId);
}


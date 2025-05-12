package com.thekingmoss.dto.orderDetail;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class OrderDetailRequestDto {
	private Long productId;
	private Integer quantity;
}

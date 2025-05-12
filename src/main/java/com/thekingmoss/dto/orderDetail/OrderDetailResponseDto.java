package com.thekingmoss.dto.orderDetail;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailResponseDto {
		private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    
    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    //TODO: add discount method

}

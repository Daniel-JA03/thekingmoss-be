package com.thekingmoss.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "province", nullable = false)
	private String province;
	
	@Column(name = "district", nullable = false)
	private String district;
	
	@Column(name = "address_line", nullable = false)
	private String addressLine;
	
	@Column(name = "address_reference", nullable = false)
	private String addressReference;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "address_type", nullable = false)
	private AddressType addressType;
}



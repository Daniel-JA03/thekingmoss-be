package com.thekingmoss.controller;

import com.thekingmoss.dto.address.AddressRequestDTO;
import com.thekingmoss.dto.address.AddressResponseDTO;
import com.thekingmoss.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final IAddressService service;
   
    @GetMapping()
    public List<AddressResponseDTO> listAddresses() {
        return service.listAddresses();
    }

    @GetMapping("/u{id}")
    public List<AddressResponseDTO> listAddresses(@PathVariable Long id) {
        return service.listAddressesByUserId(id);
    }
   
    @GetMapping("/{id}")
    public AddressResponseDTO getAddressById(@PathVariable Long id) {
        return service.getAddressById(id);
    }
  
    @PostMapping
    public AddressResponseDTO saveAddress(@RequestBody AddressRequestDTO requestDTO) {
        return service.saveAddress(requestDTO);
    }

    @PutMapping("/{id}")
    public AddressResponseDTO updateAddressById(@PathVariable Long id, @RequestBody AddressRequestDTO requestDTO) {
        return service.updateAddressById(id, requestDTO);
    }
   
    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Long id) {
        service.deleteAddressById(id);
    }
}

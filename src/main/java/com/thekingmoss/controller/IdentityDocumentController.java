package com.thekingmoss.controller;

import com.thekingmoss.dto.identitydocument.IdentityDocumentRequestDTO;
import com.thekingmoss.dto.identitydocument.IdentityDocumentResponseDTO;
import com.thekingmoss.service.IIdentityDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
public class IdentityDocumentController {
    private final IIdentityDocumentService identityDocumentService;

    @GetMapping
    public List<IdentityDocumentResponseDTO> listIdentityDocuments() {
        return identityDocumentService.listIdentityDocuments();
    }

    @GetMapping("/{id}")
    public IdentityDocumentResponseDTO getIdentityDocumentById(@PathVariable Long id) {
        return identityDocumentService.getIdentityDocumentById(id);
    }

    @PostMapping
    public IdentityDocumentResponseDTO saveIdentityDocument(@RequestBody IdentityDocumentRequestDTO requestDTO) {
        return identityDocumentService.saveIdentityDocument(requestDTO);
    }

    @PutMapping("/{id}")
    public IdentityDocumentResponseDTO updateIdentityDocumentById(@PathVariable Long id,
                                                                  @RequestBody IdentityDocumentRequestDTO requestDTO) {
        return identityDocumentService.updateIdentityDocumentById(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteIdentityDocumentById(@PathVariable Long id) {
        identityDocumentService.deleteIdentityDocumentById(id);
    }
}

package com.thekingmoss.service.impl;

import com.thekingmoss.dto.role.RoleRequestDto;
import com.thekingmoss.dto.role.RoleResponseDto;
import com.thekingmoss.entity.Role;
import com.thekingmoss.exception.ResourceNotFoundException;
import com.thekingmoss.mapper.role.RoleMapper;
import com.thekingmoss.repository.IRoleRepository;
import com.thekingmoss.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {


    private final IRoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponseDto createRole(RoleRequestDto requestDto) {
        Role role = roleMapper.toRoleEntity(requestDto);
        role = roleRepository.save(role);
        return roleMapper.responseDto(role);
    }

    @Override
    public RoleResponseDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        return roleMapper.responseDto(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::responseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto updateRole(Long id, RoleRequestDto requestDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        role.setName(requestDto.getName());

        return roleMapper.responseDto(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}

package com.thekingmoss.service.impl;

import com.thekingmoss.dto.user.UserRequestDto;
import com.thekingmoss.dto.user.UserResponseDto;
import com.thekingmoss.entity.Role;
import com.thekingmoss.entity.User;
import com.thekingmoss.exception.ResourceNotFoundException;
import com.thekingmoss.mapper.user.UserMapper;
import com.thekingmoss.repository.IRoleRepository;
import com.thekingmoss.repository.IUserRepository;
import com.thekingmoss.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User user = userMapper.toUserEntity(requestDto, role);
        user = userRepository.save(user);
        return userMapper.responseDto(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.responseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::responseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setPhone(requestDto.getPhone());
        user.setEmail(requestDto.getEmail());
        user.setRole(role);

        return userMapper.responseDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}

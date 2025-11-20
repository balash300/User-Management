package az.company.usermanagement.service.impl;

import az.company.usermanagement.dto.request.UserFilterRequest;
import az.company.usermanagement.dto.request.UserCreateRequest;
import az.company.usermanagement.dto.response.UserResponse;
import az.company.usermanagement.dto.request.UserUpdateRequest;
import az.company.usermanagement.entity.Users;
import az.company.usermanagement.exception.UserNotFoundException;
import az.company.usermanagement.mapper.UserMapper;
import az.company.usermanagement.repository.UserRepository;
import az.company.usermanagement.service.UserService;
import az.company.usermanagement.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        log.info("createUser called with request: {}", request);

        Users user = userMapper.toEntity(request);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        log.info("User saved successfully with id: {}", user.getId());

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("getUserById called with id: {}", id);

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("getAllUsers called");

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        log.info("updateUser called with id: {} and request: {}", id, request);

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserFromRequest(request, user);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        log.info("User updated successfully with id: {}", id);

        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser called with id: {}", id);

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setIsActive(false);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        log.info("User soft-deleted successfully with id: {}", id);
    }

    @Override
    public List<UserResponse> filterUsers(UserFilterRequest filter) {
        log.info("filterUsers called with filter: {}", filter);

        Specification<Users> s = UserSpecification.filter(filter);

        return userRepository.findAll(s)
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}

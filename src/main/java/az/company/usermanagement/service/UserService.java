package az.company.usermanagement.service;

import az.company.usermanagement.dto.request.UserFilterRequest;
import az.company.usermanagement.dto.request.UserCreateRequest;
import az.company.usermanagement.dto.response.UserResponse;
import az.company.usermanagement.dto.request.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
    List<UserResponse> filterUsers(UserFilterRequest filter);
}

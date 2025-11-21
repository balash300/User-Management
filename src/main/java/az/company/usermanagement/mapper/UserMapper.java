package az.company.usermanagement.mapper;

import az.company.usermanagement.dto.request.UserCreateRequest;
import az.company.usermanagement.dto.response.UserResponse;
import az.company.usermanagement.dto.request.UserUpdateRequest;
import az.company.usermanagement.entity.Users;
import az.company.usermanagement.kafka.event.UserCreatedEvent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toEntity(UserCreateRequest request);

    UserResponse toResponse(Users users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget Users users);
}

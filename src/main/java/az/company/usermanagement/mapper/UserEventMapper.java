package az.company.usermanagement.mapper;

import az.company.usermanagement.entity.Users;
import az.company.usermanagement.kafka.event.UserCreatedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEventMapper {

    UserCreatedEvent toUserCreatedEvent(Users user);
}

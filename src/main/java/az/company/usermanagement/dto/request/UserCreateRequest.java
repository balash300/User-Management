package az.company.usermanagement.dto.request;

import az.company.usermanagement.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
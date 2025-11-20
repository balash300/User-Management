package az.company.usermanagement.dto.request;

import az.company.usermanagement.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private Boolean isActive;
}
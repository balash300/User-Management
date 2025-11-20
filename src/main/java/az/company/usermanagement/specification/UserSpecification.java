package az.company.usermanagement.specification;

import az.company.usermanagement.dto.request.UserFilterRequest;
import az.company.usermanagement.entity.Users;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {

    public static Specification<Users> filter(UserFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (hasText(filter.getEmail())) {
                predicates.add(cb.like(
                        cb.lower(root.get("email")),
                                "%" + filter.getEmail().toLowerCase() + "%")
                );
            }

            if (hasText(filter.getFirstName())) {
                predicates.add(
                        cb.like(cb.lower(root.get("firstName")),
                                "%" + filter.getFirstName().toLowerCase() + "%")
                );
            }

            if (hasText(filter.getLastName())) {
                predicates.add(
                        cb.like(cb.lower(root.get("lastName")),
                                "%" + filter.getLastName().toLowerCase() + "%")
                );
            }

            if (filter.getRole() != null) {
                predicates.add(
                        cb.equal(root.get("role"), filter.getRole())
                );
            }

            if (filter.getIsActive() != null) {
                predicates.add(
                        cb.equal(root.get("isActive"), filter.getIsActive())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
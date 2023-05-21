package quan.customerservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quan.customerservice.repository.CustomerRepository;
import quan.customerservice.validation.constraint.UsernameConstraint;

import java.util.regex.Pattern;
@Component
@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {
    private final CustomerRepository customerRepository;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{7,29}$");

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null) {
            return false;
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            return false;
        }

        if (customerRepository.findByUsername(username) != null) {
            return false;
        }

        return true;
    }
}

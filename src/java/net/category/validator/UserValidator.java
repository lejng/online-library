package net.category.validator;

import net.category.model.User;
import net.category.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link net.category.model.User} class,
 * implements {@link org.springframework.validation.Validator} interface.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getEmail().length() < 8 || user.getEmail().length() > 32 || !user.getEmail().contains("@")) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "Required");
        if (user.getEmail().length() < 3 || user.getEmail().length() > 50 ) {
            errors.rejectValue("city", "Field.Incorrect");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "Required");
        if (user.getEmail().length() < 3 || user.getEmail().length() > 50 ) {
            errors.rejectValue("country", "Field.Incorrect");

        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (user.getEmail().length() < 3 || user.getEmail().length() > 50 ) {
            errors.rejectValue("name", "Field.Incorrect");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Required");
        if (user.getEmail().length() < 3 || user.getEmail().length() > 50 ) {
            errors.rejectValue("surname", "Field.Incorrect");

        }

        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}

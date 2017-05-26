package net.category.validator;

import net.category.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (book.getName().length() < 3 || book.getName().length() > 200 ) {
            errors.rejectValue("name", "Book.Name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "Required");
        if (book.getAuthor().length() < 3 || book.getName().length() > 200 ) {
            errors.rejectValue("author", "Book.Author");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Required");
        if (book.getDescription().length() < 10 || book.getName().length() > 500 ) {
            errors.rejectValue("description", "Book.Description");
        }
    }
}

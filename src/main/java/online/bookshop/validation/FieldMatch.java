package online.bookshop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldMatchValidation.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
    String message() default "Field values do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();

    String fieldMatch();
}

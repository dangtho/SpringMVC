package vn.hoidanit.laptopshop.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
  String message() default "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

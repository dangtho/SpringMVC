package vn.hoidanit.laptopshop.service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.RegisterDto;
import vn.hoidanit.laptopshop.service.UserService;

public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDto> {
  
  private UserService userService;
  public RegisterValidator(UserService userService) {
    this.userService = userService;
  }
  @Override
  public boolean isValid(RegisterDto registerDto, ConstraintValidatorContext context) {
    // validate first name and last name
    if (registerDto.getFirstName().isEmpty() || registerDto.getLastName().isEmpty()) {
      context.buildConstraintViolationWithTemplate("First name and last name must be not empty")
        .addPropertyNode("firstName")
        .addConstraintViolation();
      context.buildConstraintViolationWithTemplate("First name and last name must be not empty")
        .addPropertyNode("lastName")
        .addConstraintViolation();
      return false;
    }
    // validate password and confirm password
    if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
      context.buildConstraintViolationWithTemplate("Password and confirm password must be the same")
        .addPropertyNode("confirmPassword")
        .addConstraintViolation();
      return false;
    }
    // validate email exists
    if (this.userService.checkEmailExists(registerDto.getEmail())) {
      context.buildConstraintViolationWithTemplate("Email already exists")
        .addPropertyNode("email")
        .addConstraintViolation();
      return false;
    }
    return true;
  }
}

package com.eamcet.counselling.guide.validation;

import com.eamcet.counselling.guide.utility.InputMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import javax.swing.InputMap;

public class CategoryValidation implements ConstraintValidator<CategoryValidator, String> {
  @Override
  public boolean isValid(String Category, ConstraintValidatorContext context) {
    return InputMapper.checkCategory(Category);
  }
}

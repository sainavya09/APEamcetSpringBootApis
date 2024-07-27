package com.eamcet.counselling.guide.validation;

import com.eamcet.counselling.guide.repository.Eamcet2018Repo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class InstNameValidation
    implements ConstraintValidator<InstNameValidator, String> {
    private Eamcet2018Repo eamcet2018Repo;

  @Override
  public boolean isValid(String instName, ConstraintValidatorContext context) {
    return eamcet2018Repo.existsByInstName(instName);
  }
}

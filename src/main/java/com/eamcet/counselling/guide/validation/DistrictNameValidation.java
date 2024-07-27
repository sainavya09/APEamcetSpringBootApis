package com.eamcet.counselling.guide.validation;

import com.eamcet.counselling.guide.utility.InputMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DistrictNameValidation implements ConstraintValidator<DistrictNameValidator, String> {

  @Override
  public boolean isValid(String districtName, ConstraintValidatorContext context) {
    return InputMapper.DISTRICT_MAP.containsKey(districtName);
  }
}

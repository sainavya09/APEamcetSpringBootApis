package com.eamcet.counselling.guide.validation;

import com.eamcet.counselling.guide.repository.Eamcet2018Repo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class PlaceValidation implements ConstraintValidator<PlaceValidator, String> {

  private Eamcet2018Repo eamcet2018Repo;

  @Override
  public boolean isValid(String place, ConstraintValidatorContext context) {
    return eamcet2018Repo.existsByPlace(place);
  }
}

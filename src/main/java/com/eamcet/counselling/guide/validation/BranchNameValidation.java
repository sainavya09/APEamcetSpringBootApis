package com.eamcet.counselling.guide.validation;

import com.eamcet.counselling.guide.repository.Eamcet2018Repo;
import com.eamcet.counselling.guide.utility.InputMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;


public class BranchNameValidation implements ConstraintValidator<BranchNameValidator, String> {

  @Override
  public boolean isValid(String branchName, ConstraintValidatorContext context) {
    return InputMapper.BRANCH_MAP.containsKey(branchName);
  }
}

package com.eamcet.counselling.guide.controller;

import com.eamcet.counselling.guide.dto.output.InstituteDetailsDTO;
import com.eamcet.counselling.guide.dto.output.InstituteWithEstdDateDTO;
import com.eamcet.counselling.guide.dto.output.MatcherDTO;
import com.eamcet.counselling.guide.exception.ApplicationException;
import com.eamcet.counselling.guide.service.Eamcet2018Service;
import com.eamcet.counselling.guide.validation.AffiliatedValidator;
import com.eamcet.counselling.guide.validation.BranchNameValidator;
import com.eamcet.counselling.guide.validation.CategoryValidator;
import com.eamcet.counselling.guide.validation.DistrictNameValidator;
import com.eamcet.counselling.guide.validation.InstNameValidator;
import com.eamcet.counselling.guide.validation.PlaceValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("2018")
@AllArgsConstructor
@Validated
public class Eamcet2018Controller {
  private Eamcet2018Service eamcet2018Service;

  @GetMapping("/getClosingRank")
  public ResponseEntity<Integer> getClosingRank(
      @RequestParam @NotBlank(message = "IntName Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "InstName must be only Alphabets")
      @InstNameValidator
      String instName,
      @RequestParam @NotBlank(message = "branchName Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "branchName must be only Alphabets")
      String branchName,
      @RequestParam @NotBlank(message = "category Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+(_[a-zA-Z]+)*$", message = "category must be only Alphabets")
      @CategoryValidator
      String category) throws ApplicationException {
    log.info(
        "Hit the getClosingRank controller method with data: instName={}, branchName={}, category={}",
        instName, branchName, category);
    return new ResponseEntity<>(eamcet2018Service.getClosingRank(instName, branchName, category),
        HttpStatus.OK);
  }

  @GetMapping("/getCollegesByDistrict")
  public ResponseEntity<List<String>> getCollegesByDistrict(
      @RequestParam @NotBlank(message = "districtName Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "districtName must be only Alphabets")
      @DistrictNameValidator
      String districtName) {
    log.info("Request received to fetch colleges by district: {}", districtName);
    return new ResponseEntity<>(eamcet2018Service.getCollegesByDistrict(districtName),
        HttpStatus.OK);
  }

  @GetMapping("/getCollegesByPlace")
  public ResponseEntity<List<String>> getCollegesByPlace(
      @RequestParam @NotBlank(message = "place Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "place must be only Alphabets")
      @PlaceValidator
      String place) {
    log.info("Request received to fetch colleges by place: {}", place);
    return new ResponseEntity<>(eamcet2018Service.getCollegesByPlace(place), HttpStatus.OK);
  }

  @GetMapping("/getCollegesByAffiliation")
  public ResponseEntity<List<String>> getCollegesByAffiliation(
      @RequestParam @NotBlank(message = "affiliated Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "affiliated must be only Alphabets")
      @AffiliatedValidator
      String affiliated) {
    log.info("Request received to fetch colleges by affiliated: {}", affiliated);
    return new ResponseEntity<>(eamcet2018Service.getCollegesByAffiliation(affiliated),
        HttpStatus.OK);
  }

  @GetMapping("/getCollegesByEstd")
  public ResponseEntity<List<InstituteWithEstdDateDTO>> getCollegesByEstd(
      @RequestParam
      @Min(value = 1926, message = "There are no Colleges On or before This year") int estd) {
    log.info("Request received to fetch colleges by ESTD: {}", estd);
    return new ResponseEntity<>(eamcet2018Service.getCollegesByEstd(estd), HttpStatus.OK);
  }

  @GetMapping("/getMatchersByCategory")
  public ResponseEntity<List<MatcherDTO>> getMatchersByCategory(
      @RequestParam @NotBlank(message = "category Can't be Null Or Empty")
      @Pattern(regexp = "^^[a-zA-Z]+(_[a-zA-Z]+)*$", message = "category must be only Alphabets") String category,
      @Min(value = 1, message = "The Minimum Rank Value is Always More Than 1")
      int rank,
      @RequestParam @NotBlank(message = "branchName Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "branchName must be only Alphabets")
      @BranchNameValidator
      String branchName) {
    log.info("Request received to fetch matchers for category: {}, rank: {}, branchName: {}",
        category, rank, branchName);
    return new ResponseEntity<>(eamcet2018Service.getMatchersByCategory(category, rank, branchName),
        HttpStatus.OK);
  }

  @GetMapping("/getCollegeDetails")
  public ResponseEntity<InstituteDetailsDTO> getCollegeDetails(
      @RequestParam @NotBlank(message = "instName Can't be Null Or Empty")
      @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "instName must be only Alphabets")
      @InstNameValidator
      String instName) {
    log.info("Request received to fetch details by collegeName: {}", instName);
    return new ResponseEntity<>(eamcet2018Service.getCollegesDetailsByInstName(instName),
        HttpStatus.OK);
  }
}
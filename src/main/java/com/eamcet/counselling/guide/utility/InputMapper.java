package com.eamcet.counselling.guide.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputMapper {
  public static final Map<String, String> BRANCH_MAP;
  public static final Map<String, String> DISTRICT_MAP;
  public static final Map<String, String> AFFILIATELD_MAP;

  static {
    BRANCH_MAP = new HashMap<>();
    AFFILIATELD_MAP = new HashMap<>();
    DISTRICT_MAP = new HashMap<>();
    BRANCH_MAP.put("Agriculture", "AGR");
    BRANCH_MAP.put("Aerospace Engineering", "ASE");
    BRANCH_MAP.put("Automobile Engineering", "AUT");
    BRANCH_MAP.put("Biotechnology", "BIO");
    BRANCH_MAP.put("Computer Engineering", "CEE");
    BRANCH_MAP.put("Ceramic Engineering", "CER");
    BRANCH_MAP.put("Chemical Engineering", "CHE");
    BRANCH_MAP.put("Civil Engineering", "CIT");
    BRANCH_MAP.put("Civil Engineering Technology", "CIV");
    BRANCH_MAP.put("Chemical and Petroleum Engineering", "CPE");
    BRANCH_MAP.put("Computer Science and Engineering", "CSE");
    BRANCH_MAP.put("Computer Science", "CSS");
    BRANCH_MAP.put("Computer Science and Technology", "CST");
    BRANCH_MAP.put("Electronics and Communication Engineering", "ECE");
    BRANCH_MAP.put("Electronics and Computer Engineering", "ECM");
    BRANCH_MAP.put("Electrical and Electronics Engineering", "EEE");
    BRANCH_MAP.put("Electronics and Instrumentation Engineering", "EIE");
    BRANCH_MAP.put("Food Technology", "FDT");
    BRANCH_MAP.put("Genetic Engineering", "GIN");
    BRANCH_MAP.put("Information Technology", "INF");
    BRANCH_MAP.put("Instrumentation Engineering", "IST");
    BRANCH_MAP.put("Mechanical Engineering", "MEC");
    BRANCH_MAP.put("Metallurgical Engineering", "MET");
    BRANCH_MAP.put("Mining Engineering", "MIN");
    BRANCH_MAP.put("Multimedia Technology", "MMT");
    BRANCH_MAP.put("Naval Architecture and Marine Engineering", "NAM");
    BRANCH_MAP.put("Petrochemical Engineering", "PET");
    BRANCH_MAP.put("Pharm D", "PHD");
    BRANCH_MAP.put("Pharmacy", "PHM");
    DISTRICT_MAP.put("Anantapur", "ATP");
    DISTRICT_MAP.put("Chittoor", "CTR");
    DISTRICT_MAP.put("East Godavari", "EG");
    DISTRICT_MAP.put("Guntur", "GTR");
    DISTRICT_MAP.put("Kadapa", "KDP");
    DISTRICT_MAP.put("Kurnool", "KNL");
    DISTRICT_MAP.put("Krishna", "KRI");
    DISTRICT_MAP.put("Nellore", "NLR");
    DISTRICT_MAP.put("Prakasam", "PKS");
    DISTRICT_MAP.put("Srikakulam", "SKL");
    DISTRICT_MAP.put("Visakhapatnam", "VSP");
    DISTRICT_MAP.put("Vizianagaram", "VZM");
    DISTRICT_MAP.put("West Godavari", "WG");
    AFFILIATELD_MAP.put("Adikavi Nannaya University", "AKNU");
    AFFILIATELD_MAP.put("Acharya Nagarjuna University", "ANU");
    AFFILIATELD_MAP.put("Andhra University", "AU");
    AFFILIATELD_MAP.put("Dr. B.R. Ambedkar University", "BRAU");
    AFFILIATELD_MAP.put("Jawaharlal Nehru Technological University Anantapur", "JNTUA");
    AFFILIATELD_MAP.put("Jawaharlal Nehru Technological University Kakinada", "JNTUK");
    AFFILIATELD_MAP.put("Krishna University", "KSUM");
    AFFILIATELD_MAP.put("Sri Krishnadevaraya University", "SKU");
    AFFILIATELD_MAP.put("Sri Venkateswara University", "SVU");
    AFFILIATELD_MAP.put("Yogi Vemana University", "YGVU");
  }

  private static final List<String> keyValues = new ArrayList<>(
      Arrays.asList("OC_BOYS", "OC_GIRLS", "SC_BOYS", "SC_GIRLS", "ST_BOYS", "ST_GIRLS", "BCA_BOYS",
          "BCA_GIRLS", "BCB_BOYS", "BCB_GIRLS", "BCC_BOYS", "BCC_GIRLS", "BCD_BOYS", "BCD_GIRLS",
          "BCE_BOYS", "BCE_GIRLS"));

  public static boolean checkCategory(String Category){
    return keyValues.contains(Category);
  }

  public static String getBranchCode(String branchName) {
    return BRANCH_MAP.get(branchName);
  }

  public static String getDistrictCode(String districtName) {
    return DISTRICT_MAP.get(districtName);
  }

  public static String getAffiliatedCode(String affiliated) {
    return AFFILIATELD_MAP.get(affiliated);
  }
}
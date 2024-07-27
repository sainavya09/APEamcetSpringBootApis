package com.eamcet.counselling.guide.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eamcet.counselling.guide.dto.output.MatcherDTO;
import com.eamcet.counselling.guide.repository.Eamcet2018Repo;
import com.eamcet.counselling.guide.service.Eamcet2018Service;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(Eamcet2018Controller.class)
class Eamcet2018ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private Eamcet2018Service eamcet2018Service;

  @MockBean
  private Eamcet2018Repo eamcet2018Repo;

  @Test
  void testGetClosingRankWithParameters() throws Exception {
    String instName = "VIKAS GROUP OF INSTITUTIONS";
    String branchName = "Pharmacy";
    String category = "OC_BOYS";
    int expectedRank = 10;
    when(eamcet2018Repo.existsByInstName(anyString())).thenReturn(true);
    when(eamcet2018Service.getClosingRank(instName, branchName, category)).thenReturn(expectedRank);
    mockMvc.perform(get("/2018/getClosingRank")
            .param("instName", instName)
            .param("branchName", branchName)
            .param("category", category))
        .andExpect(status().isOk());
  }

  @Test
  void testGetClosingRankWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getClosingRank"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetCollegesByDistrictWithParameters() throws Exception {
    String districtName = "Nellore";
    when(eamcet2018Service.getCollegesByDistrict(districtName)).thenReturn(anyList());
    mockMvc.perform(get("/2018/getCollegesByDistrict")
            .param("districtName", districtName))
        .andExpect(status().isOk());
  }

  @Test
  void testGetCollegesByDistrictWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getCollegesByDistrict"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetCollegesByPlaceWithParameters() throws Exception {
    String place = "PEDDAPURAM";
    when(eamcet2018Service.getCollegesByPlace(place)).thenReturn(anyList());
    mockMvc.perform(get("/2018/getCollegesByPlace")
            .param("place", place))
        .andExpect(status().isOk());
  }

  @Test
  void testGetCollegesByPlaceWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getCollegesByPlace"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetCollegesByAffiliationWithParameters() throws Exception {
    String affiliated = "Tirupathi";
    when(eamcet2018Service.getCollegesByAffiliation(affiliated)).thenReturn(anyList());
    mockMvc.perform(get("/2018/getCollegesByAffiliation")
            .param("affiliated", affiliated))
        .andExpect(status().isOk());
  }

  @Test
  void testGetCollegesByAffiliationWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getCollegesByAffiliation"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetCollegesByEstdWithParameters() throws Exception {
    int estd = 1998;
    when(eamcet2018Service.getCollegesByEstd(estd)).thenReturn(anyList());
    mockMvc.perform(get("/2018/getCollegesByEstd")
            .param("estd", String.valueOf(estd)))
        .andExpect(status().isOk());
  }

  @Test
  void testGetCollegesByEstdWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getCollegesByEstd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetMatchersByCategoryWithParameters() throws Exception {
    String branchName = "Pharmacy";
    String category = "OC_BOYS";
    int rank = 10;
    List<MatcherDTO> matchers = new ArrayList<>();
    MatcherDTO matcherDTO = new MatcherDTO();
    matchers.add(matcherDTO);
    when(eamcet2018Service.getMatchersByCategory(branchName, rank, category)).thenReturn(matchers);
    mockMvc.perform(get("/2018/getMatchersByCategory")
            .param("branchName", branchName)
            .param("rank", String.valueOf(rank))
            .param("category", category))
        .andExpect(status().isOk());
  }

  @Test
  void testGetMatchersByCategoryWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getMatchersByCategory"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testGetCollegesDetailsByInstNameWithParameters() throws Exception {
    String instName = "VIKAS GROUP OF INSTITUTIONS";
    when(eamcet2018Service.getCollegesDetailsByInstName(instName)).thenReturn(any());
    mockMvc.perform(get("/2018/getCollegeDetails")
            .param("instName", instName))
        .andExpect(status().isOk());
  }

  @Test
  void testGetCollegesDetailsByInstNameWithNoParameters() throws Exception {
    mockMvc.perform(get("/2018/getCollegeDetails"))
        .andExpect(status().isBadRequest());
  }

}

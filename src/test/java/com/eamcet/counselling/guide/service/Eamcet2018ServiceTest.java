package com.eamcet.counselling.guide.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.eamcet.counselling.guide.dto.output.InstituteNameDTO;
import com.eamcet.counselling.guide.exception.ApplicationException;
import com.eamcet.counselling.guide.repository.Eamcet2018Repo;
import com.eamcet.counselling.guide.utility.InputMapper;

@ExtendWith(MockitoExtension.class)
class Eamcet2018ServiceTest {

	@InjectMocks
	private Eamcet2018Service eamcet2018Service;
	
	@Mock
	private Eamcet2018Repo eamcet2018Repo;
	
	@Mock
	private MongoTemplate mongoTemplate;
	
	@Mock
	private InputMapper codeMapper;
	
	@Test
	void testGetClosingRankByCategoryWithValidInputs() throws ApplicationException {
		String instName = "VIKAS GROUP OF INSTITUTIONS";
        String branchName = "Pharmacy";
        String category = "OC_BOYS";
        Integer expectedClosingRank = 10;

        Document mockDocument = new Document();
        mockDocument.put(category, expectedClosingRank);
        when(mongoTemplate.findOne(any(Query.class), eq(Document.class), eq("2018"))).thenReturn(mockDocument);
        Integer actualClosingRank = eamcet2018Service.getClosingRank(instName, branchName, category);
        assertEquals(expectedClosingRank, actualClosingRank);
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Document.class), eq("2018"));
	}
	
	@Test
	void testGetClosingRankByCategoryWithInValidInputs() throws ApplicationException {
		String instName = "VIKAS GROUP OF INSTITUTIONS";
        String branchName = "Pharmacy";
        String category = "OC_BOYS";
        Integer expectedClosingRank = 10;

        Document mockDocument = new Document();
        mockDocument.put(category, expectedClosingRank);
        when(mongoTemplate.findOne(any(Query.class), eq(Document.class), eq("2018"))).thenReturn(null);
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
        	eamcet2018Service.getClosingRank(instName, branchName, category);
        });
        assertEquals("No data found with given inputs", exception.getMessage());
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Document.class), eq("2018"));
	}
	
	@Test
	void testGetCollegesByDistrict() throws ApplicationException{
		String districtName = "Ananthapur";
		String districtCode = InputMapper.getDistrictCode(districtName);
		List<InstituteNameDTO> institutes = new ArrayList<>();
		InstituteNameDTO institute = new InstituteNameDTO();
		institutes.add(institute);
		when(eamcet2018Repo.findInstNameByDistrict(districtCode)).thenReturn(institutes);
		eamcet2018Service.getCollegesByDistrict(districtCode);
		verify(eamcet2018Repo,times(1)).findInstNameByDistrict(districtCode);
	}
}

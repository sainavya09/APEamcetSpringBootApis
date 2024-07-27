package com.eamcet.counselling.guide.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.eamcet.counselling.guide.dto.output.InstituteDetailsDTO;
import com.eamcet.counselling.guide.dto.output.InstituteNameDTO;
import com.eamcet.counselling.guide.dto.output.InstituteWithEstdDateDTO;
import com.eamcet.counselling.guide.dto.output.MatcherDTO;
import com.eamcet.counselling.guide.exception.ApplicationException;
import com.eamcet.counselling.guide.repository.Eamcet2018Repo;
import com.eamcet.counselling.guide.utility.InputMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class Eamcet2018Service {
	private Eamcet2018Repo eamcet2018Repo;
	private MongoTemplate mongoTemplate;
	private static final String INST_NAME = "inst_name";
	private static final String BRANCH_NAME = "branch_code";

	public Integer getClosingRank(String instName, String branchName, String category) throws ApplicationException {
		String branchCode = InputMapper.getBranchCode(branchName);
		Document result = queryClosingRank(instName, category, branchCode);
		Integer closingRank = Optional.ofNullable(result).map(document -> document.getInteger(category))
				.orElseThrow(() -> new ApplicationException("No data found with given inputs"));
		log.info("Closing rank retrieved for instName: {}, branchName: {}, category: {}: {}", instName, branchName,
				category, closingRank);
		return closingRank;
	}

	private Document queryClosingRank(String instName, String category, String branchCode) {
		Query query = new Query(Criteria.where(INST_NAME).is(instName).and(BRANCH_NAME).is(branchCode));
		query.fields().include(category).exclude("_id");
		Document result = mongoTemplate.findOne(query, Document.class, "2018");
		log.info("Queried closing rank for instName: {}, branchCode: {}, category: {}", instName, branchCode, category);
		return result;
	}

	public List<String> getCollegesByDistrict(String districtName) {
		String districtCode = InputMapper.getDistrictCode(districtName);
		List<InstituteNameDTO> colleges = eamcet2018Repo.findInstNameByDistrict(districtCode);
		log.info("Retrieved colleges by districtName: {} with districtCode: {}", districtName, districtCode);
		return colleges.stream().map(InstituteNameDTO::getInstName).distinct().toList();
	}

	public List<String> getCollegesByPlace(String place) {
		List<InstituteNameDTO> colleges = eamcet2018Repo.findInstNameByPlace(place);
		log.info("Retrieved colleges by place: {}", place);
		return colleges.stream().map(InstituteNameDTO::getInstName).distinct().toList();
	}

	public List<String> getCollegesByAffiliation(String affiliatedCollege) {
		String affiliated = InputMapper.getAffiliatedCode(affiliatedCollege);
		List<InstituteNameDTO> colleges = eamcet2018Repo.findInstNameByAffliated(affiliated);
		log.info("Retrieved college names by affiliation: {}", affiliatedCollege);
		return colleges.stream().map(InstituteNameDTO::getInstName).distinct().toList();
	}

	public List<InstituteWithEstdDateDTO> getCollegesByEstd(int estd) {
		List<InstituteWithEstdDateDTO> colleges = eamcet2018Repo.findAllByEstdLessThanEqual(estd);
		log.info("Retrieved colleges by established date less than or equal to: {}", estd);
		return colleges.stream().distinct().toList();
	}

	public InstituteDetailsDTO getCollegesDetailsByInstName(String instName) {
		InstituteDetailsDTO instituteDetails = eamcet2018Repo.findFirstByInstName(instName);
		log.info("Retrieved college details by institute name: {}", instName);
		return instituteDetails;
	}

	public List<MatcherDTO> getMatchersByCategory(String category, int rank, String branchName) {
		String branchCode = InputMapper.getBranchCode(branchName);
		List<Document> result = queryForMatchers(category, branchCode);
		return buildMatchers(category, rank, branchName, result);
	}

	private List<Document> queryForMatchers(String category, String branchCode) {
		Query query = new Query(Criteria.where(BRANCH_NAME).is(branchCode));
		query.fields().include(category, INST_NAME, "AFFILIATED").exclude("_id");
		log.info("Querying matchers by category: {}", category);
		return mongoTemplate.find(query, Document.class, "2018");
	}

	private List<MatcherDTO> buildMatchers(String category, int rank, String branchName, List<Document> result) {
		return result.stream()
	            .map(document -> buildMatcherFromDocument(category, rank, branchName, document))
	            .toList();
	}
	
	private MatcherDTO buildMatcherFromDocument(String category, int rank, String branchName, Document document) {
	    int closingRank = Optional.ofNullable(document.getInteger(category)).orElse(0);
	    int statusCode = statusSetter(rank, closingRank);
	    log.info("Building matcher for institute: {}", document.getString(INST_NAME));
	    return MatcherDTO.builder()
	            .instName(document.getString(INST_NAME))
	            .affliated(document.getString("AFFILIATED"))
	            .branchName(branchName)
	            .category(category)
	            .closingRank(closingRank)
	            .statusCode(statusCode)
	            .build();
	}

	private int statusSetter(int rank, int previousRank) {
		int difference = previousRank - rank;
		log.info("Calculated the Status Code with difference {}", difference);
		return calculateStatusCode(difference);
	}

	private int calculateStatusCode(int difference) {
		if (difference >= 500) {
			return 1;
		} else if (difference < 499 &&  difference >= 0) {
			return 2;
		} else {
			return 3;
		}
	}
}

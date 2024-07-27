package com.eamcet.counselling.guide.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eamcet.counselling.guide.dto.output.InstituteDetailsDTO;
import com.eamcet.counselling.guide.dto.output.InstituteNameDTO;
import com.eamcet.counselling.guide.dto.output.InstituteWithEstdDateDTO;
import com.eamcet.counselling.guide.model.Eamcet2018;

public interface Eamcet2018Repo extends MongoRepository<Eamcet2018, String> {

//	//	L.P-1
//	Actual Error:  
//		Expected to read Document Document{{OC_BOYS=115694}} into type class java.lang.Integer but didn't find a PersistentEntity for the latter
//	Learning:
//	    The issue was direct method with query successful got proper output, but type conversion in 
//	    Background wasn't happening properly and the error directs us the issue is with persistence concept.
//	@Query(value = "{ 'instCode' : ?0, 'branchCode' : ?1 }", fields = "{ ?2 : 1, '_id' : 0 }")
//	Optional<Integer> getClosingRank(String instCode,String branchCode, String category);

	List<InstituteNameDTO> findInstNameByDistrict(String district);

	List<InstituteNameDTO> findInstNameByPlace(String place);

	List<InstituteNameDTO> findInstNameByAffliated(String affiliated);

	List<InstituteWithEstdDateDTO> findAllByEstdLessThanEqual(int estd);

	// If we want only one entity we can add suffix as findFirst
	InstituteDetailsDTO findFirstByInstName(String instName);

	boolean existsByInstName(String instName);

	boolean existsByPlace(String place);

	boolean existsByAffliated(String affliated);
}
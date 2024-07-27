package com.eamcet.counselling.guide.dto.output;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InstituteDetailsDTO {
	String instName;
	String instCode;
	String branchCode;
	String type;
	String region;
	String district;
	String place;
	String coEducation;
	String affliated;
	Integer estd;
}

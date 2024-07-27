package com.eamcet.counselling.guide.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "2018")
public class Eamcet2018 {
	@Field(name = "SNO")
	int sno;
	@Field(name = "inst_code")
	String instCode;
	@Field(name = "inst_name")
	String instName;
	String type;
	@Field(name = "REG")
	String region;
	@Field(name = "DIST")
	String district;
	@Field(name = "PLACE")
	String place;
	@Field(name = "COED")
	String coEducation;
	@Field(name = "AFFLIATED")
	String affliated;
	@Field(name = "ESTD")
	int estd;
	@Field(name = "branch_code")
	String branchCode;
	@Field(name = "OC_BOYS")
	Integer ocBoys;
	@Field(name = "OC_GIRLS")
	Integer ocGirls;
	@Field(name = "SC_BOYS")
	Integer scBoys;
	@Field(name = "SC_GIRLS")
	Integer scGirls;
	@Field(name = "ST_BOYS")
	Integer stBoys;
	@Field(name = "ST_GIRLS")
	Integer stGirls;
	@Field(name = "BCA_BOYS")
	Integer bcaBoys;
	@Field(name = "BCA_GIRLS")
	Integer bcaGirls;
	@Field(name = "BCB_BOYS")
	Integer bcbBoys;
	@Field(name = "BCB_GIRLS")
	Integer bcbGirls;
	@Field(name = "BCC_BOYS")
	Integer bccBoys;
	@Field(name = "BCC_GIRLS")
	Integer bccGirls;
	@Field(name = "BCD_BOYS")
	Integer bcdBoys;
	@Field(name = "BCD_GIRLS")
	Integer bcdGirls;
	@Field(name = "BCE_BOYS")
	Integer bceBoys;
	@Field(name = "BCE_GIRLS")
	Integer bceGirls;
}

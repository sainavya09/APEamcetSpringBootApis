package com.eamcet.counselling.guide.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatcherDTO {
	String instName;
	String branchName;
	String category;
	Integer closingRank;
	String affliated;
	int statusCode;
}

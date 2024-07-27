package com.eamcet.counselling.guide.dto.output;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InstituteWithEstdDateDTO {
	private String instName;
	private int estd;
}

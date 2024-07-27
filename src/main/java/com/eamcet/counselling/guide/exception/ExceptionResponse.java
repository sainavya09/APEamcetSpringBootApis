package com.eamcet.counselling.guide.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	private String timeStamp;
	private String status;
	private String error;
	private String path;
}
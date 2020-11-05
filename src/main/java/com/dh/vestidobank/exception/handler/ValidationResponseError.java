package com.dh.vestidobank.exception.handler;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Getter
@SuperBuilder
public class ValidationResponseError extends ResponseError{

	private List<FieldError> errors;

	public ValidationResponseError(long timestamp, Integer status, String name, String message, String path,List<FieldError> errors) {
		super(timestamp, status, name, message, path);
		this.errors = errors;
		
	}
	
	
	
}

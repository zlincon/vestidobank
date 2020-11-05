package com.dh.vestidobank.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class ResponseError {
	
	private long timestamp;
	private Integer status;
	private String name;
	private String message;
	private String path;
	
}

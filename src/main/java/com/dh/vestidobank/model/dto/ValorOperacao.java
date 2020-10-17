package com.dh.vestidobank.model.dto;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValorOperacao {
	

	@Positive
	private double valor;
	
	
}

package com.dh.vestidobank.model.dto.create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class GerenteCreateDTO {

    @NotNull
    @Size(min =6, max = 60)
    private String nome;
    @Size(min = 10, max = 120)
    @NotNull
    private String endereco;
    @NotNull
    @CPF
    private String cpf;
    
    @NotNull
    @Size(min = 8)
    private String senha;
    
	@NotNull
	@Email
	private String email;

}

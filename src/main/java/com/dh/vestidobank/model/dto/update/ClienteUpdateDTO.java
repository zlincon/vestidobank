package com.dh.vestidobank.model.dto.update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteUpdateDTO {
	@NotNull
	private Long id;

    @NotNull
    @Size(min =6, max = 60)
    private String nome;
    @Size(min = 10, max = 120)
    @NotNull
    private String endereco;
}

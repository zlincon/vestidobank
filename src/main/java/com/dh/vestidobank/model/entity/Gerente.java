package com.dh.vestidobank.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gerentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Gerente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false, length = 60)
	@NotNull
	@Size(min = 6, max = 60)
	private String nome;
	
	@Column(nullable = false, length = 120)
	@NotNull
	@Size(min = 6, max = 120)
	private String endereco;
	
	@Column(nullable = false, length = 11, unique = true)
	@NotNull
	@CPF
	private String cpf;
	
	@Column(nullable = false, length = 60, unique = true)
	@NotNull
	@Email
	private String email;
	
	@OneToMany(mappedBy="gerente")
	private Set<Cliente> clientes;
	
}

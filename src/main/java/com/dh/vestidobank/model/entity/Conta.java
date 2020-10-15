package com.dh.vestidobank.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4604925791367022563L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "numero_conta", nullable = false, unique = true)
	@NotNull
	private Long numeroConta;
	private double saldo;
	@Column(name = "limite_especial")
	private double limiteEspecial;
	@Column(name = "tipo_conta", nullable = false)
	@NotNull
	private int tipoConta;
	
	@OneToOne
	@JoinColumn(name = "cliente_id", nullable = false, unique = true)
	@JsonIgnore
	private Cliente cliente;
	
	
	
	
	
	
}

package com.dh.vestidobank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dh.vestidobank.exception.ArgumentNotValidException;
import com.dh.vestidobank.exception.ObjectNotFoundException;
import com.dh.vestidobank.model.entity.Conta;
import com.dh.vestidobank.repository.ContaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContaService {
	
	
	private final ContaRepository contaRepository;
	
	public List<Conta> findAll(){
		return this.contaRepository.findAll();
	}
	
	
	public Conta findById(Long id){
		
		Optional
		.ofNullable(id)
		.orElseThrow(() -> new ArgumentNotValidException("Id não pode ser nulo") );
		
		return this.contaRepository.findById(id)
				.orElseThrow( () -> new  ObjectNotFoundException("Conta de id " + id + " não encontrado"));
	}
	

}

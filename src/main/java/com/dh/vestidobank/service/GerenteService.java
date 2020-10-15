package com.dh.vestidobank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dh.vestidobank.exception.ArgumentNotValidException;
import com.dh.vestidobank.exception.ObjectNotFoundException;
import com.dh.vestidobank.model.entity.Gerente;
import com.dh.vestidobank.repository.GerenteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GerenteService {
	
	private final GerenteRepository gerenteRepository;
	
	
	public Gerente create(Gerente gerente) {
		
		return this.gerenteRepository.save(gerente);
		
	}
	
	public Gerente update (Gerente gerente) {
		
		 findById(gerente.getId());
		 
		 return this.gerenteRepository.save(gerente);
		
	}
	
	
	public Gerente findById(Long id) {
		Optional
		     .ofNullable(id)
		     .orElseThrow( () ->  new ArgumentNotValidException("Id não pode ser nulo"));
		     
		return this.gerenteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Gerente de id " + id + " não foi encontrado"));
	}
	
	public List<Gerente> findAll(){
		return this.gerenteRepository.findAll();
	}
	
	
	
	
	public void delete(Long id) {
		this.findById(id);
		
		this.gerenteRepository.deleteById(id);
	}
	
	
	
	
   
}
